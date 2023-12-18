import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ComponentStatus, ControlsOf } from '@tulsa/libs/commons';
import { CrudImplService, Page, RequestConfig, SortBuilder } from '@tulsa/libs/crud-api';
import { PaginatorOptions } from '@tulsa/libs/mat/paginator';
import { TulsaCatalogueBaseTerm, TulsaCatalogueFacet } from '@tulsa/libs/sdk/catalogue';
import { BaseModel } from '@tulsa/libs/sdk/common';
import { debounceTime, distinctUntilChanged, filter, ReplaySubject, takeUntil } from 'rxjs';
import { CatalogueBrowserService } from '../../catalogue-browser.service';
import { SearchForm } from '../../models';


@Component({
  selector: 'tsw-cat-code-generator',
  templateUrl: './cat-code-generator.component.html',
  styleUrls: ['./cat-code-generator.component.scss'],
})
export class CatCodeGeneratorComponent implements OnInit, OnDestroy {

  termSource = new MatTableDataSource<TulsaCatalogueBaseTerm>();
  termColumns = ['selection', 'termName', 'termCode'];

  facetSource = new MatTableDataSource<TulsaCatalogueFacet>();
  facetColumns = ['selection', 'termName', 'termCode', 'attribute'];

  canGenerateCode = false;

  paginator: PaginatorOptions;
  baseTerm: TulsaCatalogueBaseTerm | undefined;
  readonly selectedTerms: SelectionModel<string>;
  readonly searchForm: FormGroup<ControlsOf<SearchForm>>;
  readonly status = new ComponentStatus('IDLE');

  private sortOptions: Sort | undefined;

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    private crudService: CrudImplService<BaseModel>,
    private fb: FormBuilder,
    private catalogueService: CatalogueBrowserService,
  ) {
    this.searchForm = this.buildForm();
    this.paginator = this.createPaginatorSource();
    this.selectedTerms = catalogueService.facets;
  }

  ngOnInit(): void {
    this.monitorServiceChanges();
    this.monitorSearchChanges();
    this.monitorResetEvent();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  resetComponent() {
    this.baseTerm = undefined;
    this.resetFacets();
    this.resetSearch();
  }

  resetSearch() {
    this.searchForm.controls.search.reset('');
  }

  resetFacets() {
    this.facetSource.data = [];
    this.termSource.data = [];
    this.sortOptions = undefined;
    const resetPage = this.createPaginatorSource();
    this.pageList(resetPage);
  }

  selectBaseTerm(value: TulsaCatalogueBaseTerm) {
    this.catalogueService.createCode(value);
    this.baseTerm = value;
    this.paginator.pageIndex = 0;
    this.sortOptions = undefined;
    this.resetSearch();
  }

  selectFacet(value: TulsaCatalogueFacet) {
    this.catalogueService.toggleFacet(value);
  }

  search() {
    if (!this.baseTerm) {
      this.loadBaseTerms();
    } else {
      this.loadFacets();
    }
  }

  sortList(ev: Sort) {
    this.sortOptions = ev;
    this.search();
  }

  pageList(ev: PageEvent) {
    this.paginator = ev;
    this.search();
  }

  private buildForm(): FormGroup<ControlsOf<SearchForm>> {
    return this.fb.group({
      search: this.fb.control(''),
      restrictHierarchy: this.fb.control(false),
    });
  }

  private loadBaseTerms() {
    const catalogue = this.catalogueService.catalogue;
    const hierarchy = this.catalogueService.hierarchy;

    if (!catalogue || !hierarchy) return;

    const page = this.paginator.pageIndex;
    const size = this.paginator.pageSize;
    const sort = this.sortOptions?.direction
      ? SortBuilder.fromArray([[this.sortOptions.active, this.sortOptions.direction]])
      : SortBuilder.fromArray([]);

    let search = this.searchForm.value.search?.trim();
    search = `%${ search }%`;
    const config: RequestConfig = {
      resourceName: 'catBaseTerm',
      queryParams: {
        search,
        catalogueId: catalogue.id,
        hierarchyId: hierarchy.id,
        page,
        size,
        sort
      }
    };
    this.status.status = 'PROCESS';
    this.crudService.findAll<TulsaCatalogueBaseTerm>(config).subscribe({
      next: page => this.afterTermsLoaded(page),
      error: () => this.status.status = 'ERROR'
    });
  }

  private loadFacets() {
    const catalogue = this.catalogueService.catalogue;
    const search = this.searchForm.value.search?.trim();

    if (!catalogue) return;

    const page = this.paginator.pageIndex;
    const size = this.paginator.pageSize;
    const sort = this.sortOptions?.direction
      ? SortBuilder.fromArray([[this.sortOptions.active, this.sortOptions.direction]])
      : SortBuilder.fromArray([]);

    const config: RequestConfig = {
      resourceName: 'catFacets',
      queryParams: {
        search: `%${ search }%`,
        catalogueId: catalogue.id,
        page,
        size,
        sort,
      },
    };

    const restrictHierarchy = this.searchForm.value.restrictHierarchy;
    const hierarchy = this.catalogueService.hierarchy;
    if (restrictHierarchy && hierarchy) {
      config.queryParams = {
        ...config.queryParams,
        hierarchyId: hierarchy.id,
      };
    }

    this.status.status = 'PROCESS';
    this.crudService.findAll<TulsaCatalogueFacet>(config).subscribe({
      next: page => this.afterFacetsLoaded(page),
      error: () => this.status.status = 'ERROR',
    });
  }

  private monitorServiceChanges() {
    this.catalogueService.canGenerateCode$.pipe(
      takeUntil(this.destroyed$),
      distinctUntilChanged(),
    ).subscribe(next => {
      if (this.canGenerateCode !== next) {
        this.canGenerateCode = next;
        // this.resetComponent();
      }
    });

    this.catalogueService.hierarchy$.pipe(
      takeUntil(this.destroyed$),
      // skip(1),
      distinctUntilChanged(),
      filter(() => !this.baseTerm)
    ).subscribe(() => this.loadBaseTerms());
  }

  private afterFacetsLoaded(page: Page<TulsaCatalogueFacet>) {
    this.facetSource.data = [...page.content];
    this.paginator.length = page.details.totalElements;
    this.status.status = 'IDLE';
  }

  private afterTermsLoaded(page: Page<TulsaCatalogueBaseTerm>) {
    this.termSource.data = [...page.content];
    this.paginator.length = page.details.totalElements;
    this.status.status = 'IDLE';
  }

  private createPaginatorSource(): PaginatorOptions {
    return {
      length: 0,
      pageIndex: 0,
      pageSize: 50,
    };
  }

  private monitorSearchChanges() {
    this.searchForm.valueChanges.pipe(
      takeUntil(this.destroyed$),
      debounceTime(400),
      distinctUntilChanged(),
    ).subscribe({
      next: () => this.search(),
      // error: () => {},
    });
  }

  private monitorResetEvent() {
    this.catalogueService.reset$
      .pipe(takeUntil(this.destroyed$))
      .subscribe(() => this.resetComponent());
  }
}
