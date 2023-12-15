import { SelectionModel } from '@angular/cdk/collections';
import { Directive, OnDestroy, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { PageEvent } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ColumnSource, ColumnSrc } from '@base/shared/collections';
import { AppliedFilter, FilterService, Query } from '@base/shared/filter';
import { AppQuery, BtnSrc } from '@libs/commons';
import { CrudService, Page, PageReqBuilder, RequestConfig, SortBuilder } from '@libs/crud-api';
import { PaginatorOptions } from '@libs/mat/paginator';
import { filter, firstValueFrom, Observable, ReplaySubject, Subscription, switchMap, takeUntil, tap } from 'rxjs';
import { fromPromise } from 'rxjs/internal/observable/innerFrom';

@Directive()
export abstract class BaseListPageComponent<T = any, ID = number> implements OnInit, OnDestroy {

  readonly source = this.createDataSource();
  readonly columns = this.createColumnSource();
  readonly selection = this.createSelectionSource();
  readonly paginator = this.createPaginatorSource();
  readonly sort = this.createSortSource();
  readonly filter = this.createFilterSource();

  loading = false;
  exportFormats: (string | BtnSrc)[] = [];
  downloadFileName = '';
  previousRequestConfig: RequestConfig | undefined;
  ongoingDataLoad: Subscription | undefined;

  abstract readonly resourceName: string;

  protected readonly destroyed$ = new ReplaySubject<boolean>(1);

  protected constructor(
      protected crudService: CrudService<T, ID>,
      protected filterService: FilterService,
      // config service
      // columns service
  ) {
  }

  async ngOnInit() {
    await this.setupComponentOptions();
    this.loadData();


    this.registerFiltersListener();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  loadData() {
    this.loading = true;
    this.ongoingDataLoad = this.fetchData().subscribe({
      next: data => this.afterFetchDataSuccess(data),
      error: e => this.afterFetchDataFailed(e),
    });
  }

  reloadData() {
    this.loading = true;
    this.ongoingDataLoad = this.fetchData().subscribe({
      next: data => this.afterReloadSuccess(data),
      error: e => this.afterReloadFailed(e),
    });
  }

  sortData(ev: Sort) {
    this.sort.active = ev.active;
    this.sort.direction = ev.direction;
    this.reloadData();
  }

  navigatePage(ev: PageEvent) {

    this.paginator.pageIndex = ev.pageIndex;
    if (ev.pageSize !== this.paginator.pageSize) {
      this.paginator.pageSize = ev.pageSize;
    }
    const source = {
      ...this.filter.query?.getSource(),
      pageIndex: ev.pageIndex,
      pageSize: ev.pageSize,
    };
    this.filterService.set({
      name: this.resourceName,
      mode: this.filter.mode,
      useParams: { handling: 'merge' },
      source
    });

    this.loading = true;
    this.ongoingDataLoad = this.fetchData().subscribe({
      next: data => this.afterNavigatePageSuccess(data),
      error: e => this.afterNavigatePageFailed(e),
    });

  }

  select(ev: MatCheckboxChange, row: T) {
    if (ev) {
      this.selection.toggle(row);
    }
  }

  selectAll(ev: MatCheckboxChange) {
    ev.checked
        ? this.selection.select(...this.source.data)
        : this.selection.clear();
  }

  protected async setupComponentOptions() {
    const config = await firstValueFrom(this.filterService.get(this.resourceName));
    if (!config) {
      return;
    }

    this.updateQuery(config);
    const { pageSize, pageIndex } = config.query.getSource();
    if (pageSize) {
      this.paginator.pageSize = +pageSize;
    }

    if (pageIndex) {
      this.paginator.pageIndex = +pageIndex;
    }
  }

  protected createDataSource() {
    return new MatTableDataSource<T>([]);
  }

  protected createColumnSource() {
    return new ColumnSource(this.getColumns());
  }

  protected createSelectionSource() {
    return new SelectionModel<T>(true, []);
  }

  protected createFilterSource() {
    return {
      name: this.resourceName,
      query: (new Query({})) as AppQuery,
    } as AppliedFilter; // to be defined
  }

  protected createPaginatorSource(): PaginatorOptions {
    return {
      length: 0,
      pageIndex: 0,
      pageSize: 50,
    };
  }

  protected createSortSource(): Sort {
      return {
        active: 'id',
        direction: 'desc',
      };
  }

  protected registerFiltersListener() {
    this.filterService.get(this.resourceName).pipe(
        takeUntil(this.destroyed$),
        filter(value => JSON.stringify(this.filter.query.getSource()) !== JSON.stringify(value.query.getSource()))
    ).subscribe(
        value => this.afterQueryDetected(value)
    );
  }

  protected fetchData(): Observable<Page<T>> {
    return fromPromise(this.getRequestConfig()).pipe(
        tap((config) => {
          this.stopOngoingDataLoad();
          // Estaría bien almacenar el request config global para la página y emitir actualizaciones en vez de sobrescribir
          this.previousRequestConfig = config;
        }),
        switchMap(config => this.crudService.findAll(config))
    );
  }

  protected afterFetchDataSuccess(data: Page<T>) {
    const ev = data.event;
    this.paginator.length = ev.length;
    this.paginator.pageSize = ev.pageSize;
    this.source.data = data.content;
    this.loading = false;
    // update state and stop loading animation
  }

  protected afterFetchDataFailed(error: unknown) {
    this.loading = false;
    // update state and stop loading animation
  }

  protected afterReloadSuccess(data: Page<T>) {
    this.afterFetchDataSuccess(data); // temp
    // update state and stop loading animation
  }

  protected afterReloadFailed(error: unknown) {
    this.afterFetchDataFailed(error);
    // update state and stop loading animation
  }

  protected afterNavigatePageSuccess(data: Page<T>) {
    this.afterFetchDataSuccess(data); // temp
    // update state and stop loading animation
  }

  protected afterNavigatePageFailed(error?: unknown) {
    this.afterFetchDataFailed(error);
    // update state and stop loading animation
  }

  protected afterQueryDetected(value: AppliedFilter) {
    this.updateQuery(value);
    this.reloadData();
  }

  protected updateQuery(value: AppliedFilter) {
    this.filter.query = value.query;
    this.filter.mode = value.mode;
  }

  protected getRequestConfig(): Promise<RequestConfig> {
    const { pageSize, pageIndex, length } = this.paginator;
    const { active, direction } = this.sort;
    const sort = (active && direction)
        ? SortBuilder.fromArray([[active, direction]])
        : [];
    const pageReq = PageReqBuilder.fromEvent({ pageSize, pageIndex, length }, sort);
    const queryParams = this.filter.query ? this.filter.query.toObject() : {};
    return Promise.resolve({
      resourceName: this.resourceName,
      queryParams,
      pageReq,
    });
  }

  protected stopOngoingDataLoad() {
    // Deshabilitado temporalmente porque su aplicación no permite realizar recargas de datos
    // if (this.ongoingDataLoad) {
    //   this.ongoingDataLoad.unsubscribe();
    //   this.ongoingDataLoad = undefined;
    // }
  }

  protected abstract getColumns(): ColumnSrc[] | Observable<ColumnSrc[]>;
}
