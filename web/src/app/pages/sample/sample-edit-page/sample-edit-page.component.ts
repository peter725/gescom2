import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FORM_STATUS } from '@base/shared/components/form';
import { NotificationService } from '@base/shared/notification';
import {
  DatasetContainer,
  DatasetForm,
  datasetTrackByFn,
  SampleDatasetManagerService
} from '@base/shared/sample-dataset';
import { AppError, ComponentStatus } from '@libs/commons';
import { filter, Observable, ReplaySubject, takeUntil, tap } from 'rxjs';
import { map } from 'rxjs/operators';
import { ConfigForm } from './sample-edit-page.model';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-sample-edit-page',
  templateUrl: './sample-edit-page.component.html',
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
  ]
})
export class SampleEditPageComponent implements OnInit, OnDestroy {
  readonly trackByFn = datasetTrackByFn;

  readonly helpIndicator$: Observable<boolean>;
  readonly configForm: FormGroup<ConfigForm>;
  readonly valuesOf = Object.entries;

  preConfigComplete = false;

  datasetForm: FormGroup<DatasetForm> | undefined;
  datasetContainer: DatasetContainer;

  private resourceId = 0;
  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    private sampleDataset: SampleDatasetManagerService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private notification: NotificationService,
    @Inject(FORM_STATUS) public status: ComponentStatus,
  ) {
    this.helpIndicator$ = sampleDataset.showHelpIndicator$;
    this.configForm = this.buildConfigForm();
    this.datasetContainer = this.sampleDataset.datasetContainer;
  }

  get isNew() {
    return !this.resourceId;
  }

  ngOnInit() {
    this.cleanupComponent();
    this.monitorResourceId();
  }

  ngOnDestroy() {
    this.destroyed$.next(true);
  }

  toggleHelpIndicator() {
    this.sampleDataset.helpIndicator = !this.sampleDataset.helpIndicator;
  }

  async confirmConfiguration() {
    if (this.configForm.valid) {
      this.status.status = 'LOAD';
      await this.completeConfig();
      await this.loadSample();
      this.status.status = 'IDLE';
    }
  }

  async save() {
    this.status.status = 'LOAD';
    try {
      const data = this.configForm.getRawValue();
      const result = await this.sampleDataset.saveSample({
        module: data.module,
        season: data.season,
        scope: data.scope,
        templateSrcId: data.template?.id,
        // programSrcId: por configurar/añadir
      });
      this.status.status = 'IDLE';
      if (this.isNew && result) {
        await this.router.navigate([`../${ result.sampleId }`], { relativeTo: this.route });
      }
    } catch (e) {
      const err = AppError.parse(e);
      this.notification.show({
        type: 'danger',
        message: err.message,
      });
      this.status.status = 'IDLE';
    }
  }

  async reloadSample() {
    this.status.status = 'LOAD';
    await this.loadSample();
    this.status.status = 'IDLE';
  }

  async summarizeErrors() {
    await this.sampleDataset.summarizeValidationErrors();
  }

  private async initializeData() {
    console.info(`Initializing data for: ${ this.resourceId }`);
    this.status.status = 'LOAD';
    if (!this.isNew) {
      await this.completeConfig();
      await this.loadSample();
    } else {
      this.preConfigComplete = false;
      this.configForm.enable();
      this.cleanupComponent();
    }
    this.status.status = 'IDLE';
  }

  private async completeConfig() {
    this.configForm.disable();
    this.preConfigComplete = true;
  }

  private async loadSample() {
    await this.sampleDataset.load({
      type: 'sample',
      id: this.resourceId,
      templateId: this.configForm.value.template?.id
    });
    this.datasetForm = this.sampleDataset.datasetForm;
  }

  private buildConfigForm() {
    return this.fb.group<ConfigForm>({
      module: this.fb.control(null, [Validators.required]),
      season: this.fb.control(null, [Validators.required]),
      scope: this.fb.control(null, [Validators.required]),
      useTemplate: this.fb.control(false, { nonNullable: true }),
      template: this.fb.control(null),
    });
  }

  private monitorResourceId() {
    this.route.paramMap.pipe(
      takeUntil(this.destroyed$),
      map(params => params.get('id') || '0'),
      map(id => +id),
      filter(id => this.resourceId !== id),
      tap(id => this.resourceId = id)
    ).subscribe(() => this.initializeData());
  }

  private cleanupComponent(){
    this.datasetForm = undefined;
    this.sampleDataset.cleanup();
  }
}
