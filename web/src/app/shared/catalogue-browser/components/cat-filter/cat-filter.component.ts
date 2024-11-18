import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomAutocompleteComponent } from '@tulsa/app/shared/select';
import { ControlsOf } from '@tulsa/libs/commons';
import { TulsaCatalogue, TulsaCatalogueHierarchy } from '@tulsa/libs/sdk/catalogue';
import { firstValueFrom, ReplaySubject, takeUntil } from 'rxjs';
import { CatalogueBrowserService } from '../../catalogue-browser.service';
import { CatFilterModel } from './cat-filter.model';


@Component({
  selector: 'tsw-cat-filter',
  templateUrl: './cat-filter.component.html',
  styleUrls: ['./cat-filter.component.scss'],
})
export class CatFilterComponent implements OnInit, OnDestroy {

  @ViewChild('hierarchySel') hierarchySelect: CustomAutocompleteComponent | undefined;

  form: FormGroup<ControlsOf<CatFilterModel>> = this.buildForm();

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    private fb: FormBuilder,
    private catalogueService: CatalogueBrowserService
  ) {
  }

  ngOnInit(): void {
    this.monitorResetEvent();
    this.setInitialValues();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  catalogueSelected(value: TulsaCatalogue | undefined) {
    if (value) {
      this.catalogueService.useCatalogue(value);
      this.loadHierarchies();
    }
    this.catalogueService.useHierarchy();
    this.form.patchValue({ hierarchy: null });
    this.toggleHierarchy();
  }

  hierarchySelected(value: TulsaCatalogueHierarchy) {
    const { catalogue } = this.form.value;
    if (catalogue && value) {
      this.catalogueService.useHierarchy(value);
    }
  }

  private toggleHierarchy() {
    if (this.form.controls.catalogue.valid) {
      this.form.controls.hierarchy.enable({ emitEvent: false });
    } else {
      this.form.controls.hierarchy.disable({ emitEvent: false });
    }
  }

  private loadHierarchies() {
    if (!this.hierarchySelect) return;

    const catalogue = this.form.value.catalogue;
    if (catalogue) {
      this.hierarchySelect.searchQueryParams = { catalogueId: catalogue.id };
    }
  }

  private monitorResetEvent() {
    this.catalogueService.reset$.pipe(takeUntil(this.destroyed$)).subscribe(() => {
      this.form.reset();
      this.toggleHierarchy();
    });
  }

  private async setInitialValues() {
    const catalogue = await firstValueFrom(this.catalogueService.catalogue$);
    if (!catalogue) return;

    this.form.patchValue({ catalogue }, { emitEvent: false });
    const hierarchy = await firstValueFrom(this.catalogueService.hierarchy$);
    if (hierarchy) {
      this.form.patchValue({ hierarchy }, { emitEvent: false });
    }
  }

  private buildForm() {
    return this.fb.group<ControlsOf<CatFilterModel>>({
      catalogue: this.fb.control(null, [Validators.required]),
      hierarchy: this.fb.control({ value: null, disabled: true }, [Validators.required]),
    });
  }
}
