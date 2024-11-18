import {Directive, inject, Inject, OnDestroy, OnInit} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FORM_STATUS } from '@base/shared/components/form';
import { AppError, ComponentStatus } from '@libs/commons';
import { ControlsOf, FormMapper } from '@libs/commons/form';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { NamedRoutes } from '@libs/named-routes';
import { filter, firstValueFrom, Observable, of, ReplaySubject, tap } from 'rxjs';
import { map } from 'rxjs/operators';
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {LiveAnnouncer} from "@angular/cdk/a11y";
import { NotificationService } from '@base/shared/notification';
import { MatDialog } from '@angular/material/dialog';
import { DataSharingService } from '@base/services/dataSharingService';
import { SharedDataService } from '@base/services/sharedDataService';


@Directive()
export abstract class EditPageBaseComponent<T, F extends Record<string, any> = any> implements OnInit, OnDestroy {

  /**
   * Specifies what API resource should the page use.
   */
  abstract readonly resourceName: string;


  /**
   * Form group used as base of the editor.
   */
  readonly form: FormGroup<ControlsOf<F>>;

  /**
   * Current data ID. Value may be a composite ID thus string.
   */
  resourceId: string | number = 0;

  /**
   * Whether to require a confirmation before saving or not.
   */
  skipSaveConfirmation = false;

  /**
   * Whether to redirect the user after saving the data
   */
  redirectAfterSave = true;


  /**
   * Path where to redirect after saving data.
   */
  redirectAfterSavePath: string[];

  /**
   * Parameter used to extract the resource ID from the
   * current path.
   */
  pathIdParamName = 'id';

  /**
   * Text displayed as header title
   */
  protected _createResourceTitle = 'generic.actions.add';
  protected _editResourceTitle = 'generic.actions.edit';

  /**
   * Raw data loaded from API.
   */
  private _srcData: T | undefined;
  /**
   * Formatted form source data.
   */
  private _startValue: F | undefined;

  /**
   *
   */
  private _activeOperation: Observable<T> | undefined;

  protected readonly destroyed$ = new ReplaySubject<boolean>(1);

  protected initializeAdditionalData(): void {
    // Método pensado para ser sobrescrito
  }


  announcer = inject(LiveAnnouncer);

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;

  constructor(
    protected crudService: CrudImplService<T>,
    protected fb: FormBuilder,
    protected route: ActivatedRoute,
    protected router: Router,
    protected namedRoutes: NamedRoutes,
    protected notification: NotificationService,
    protected sharedDataService: SharedDataService,
    protected mapper: FormMapper<T, F>,
    protected dialog: MatDialog,
    @Inject(FORM_STATUS) public status: ComponentStatus,
  ) {
    this.form = this.buildForm();
    this.redirectAfterSavePath = this.getRedirectAfterSaveRoute();
  }

  ngOnInit() {
    this.status.status = 'LOAD';
    this.loadResourceId().subscribe(() => this.loadData());
    this.initializeAdditionalData();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  resetForm() {
    this.form.reset(this.startValue);
  }

  submitForm() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.save();
  }

  setRedirectAfterSave(value: boolean) {
    this.redirectAfterSave = value;
  }

  /**
   * Registers a route param listener and loads the current resource ID
   * into the component.
   */
  protected loadResourceId(): Observable<any> {
    return this.route.paramMap.pipe(
      map(v => v.get(this.pathIdParamName) || '0'),
      filter(id => this.resourceId !== id),
      tap(id => this.resourceId = id)
    );
  }

  /**
   * Loads the current resource into the component.
   */
  protected async loadData() {
    try {
      this.resetDataBeforeLoad();

      let startValue: F;
      if (this.isNew) {
        startValue = await firstValueFrom(this.fetchCreateSrc());
      } else {
        this.srcData = await firstValueFrom(this.fetchExistingSrc());
        startValue = await firstValueFrom(this.mapModelToForm(this.srcData));
      }
      this.afterLoadDataSuccess(startValue);
    } catch (err: any) {
      this.afterLoadDataError(err);
    }
  }

  /**
   * Fetches an existing resource from the API.
   */
  protected fetchExistingSrc(): Observable<T> {

    return this.crudService.findById(+this.resourceId, {
      resourceName: this.resourceName,
      pathParams: {
        id: this.resourceId,
      }
    });

  }

  /**
   * Fetches required initial data to create the resource.
   */
  protected fetchCreateSrc(): Observable<F> {
    return of({} as F); // TBD how to handle
  }

  /**
   * Resets the previous data if a new resource is loaded.
   */
  protected resetDataBeforeLoad() {
    this.srcData = undefined;
    this.startValue = undefined;
    this.form.reset(); // Force reset to null
  }

  /**
   * Runs when the loading process has completed successfully.
   */
  protected afterLoadDataSuccess(startValue: F) {
    this.startValue = startValue;
    this.form.patchValue(startValue, { emitEvent: false });
    this.status.status = 'IDLE';
  }

  /**
   * Runs when the loading process has encountered an error.
   */
  protected afterLoadDataError(e: any) {
    this.status.status = 'ERROR';
    const err = AppError.parse(e);
    //this.notification.show({ type: 'warn', message: err.error });
  }

  /**
   * Saves current data
   */
  protected async save() {
    try {
      const payload = await this.createSavePayload();
      this.activeOperation = this.createSaveOperation(payload);

      this.status.status = 'PROCESS';
      const result = await firstValueFrom(this.activeOperation);
      await this.afterSaveSuccess(result);
    } catch (e: any) {
      await this.afterSaveError(e);
    }
  }

  /**
   * Generates a data payload based on the current form data.
   */
  protected createSavePayload() {
    const copy = { ...this.form.getRawValue() } as F;
    return firstValueFrom(this.mapFormToModel(copy));
  }

  /**
   * Generates an operation to create a new resource or update an existing one.
   */
  protected createSaveOperation(payload: T) {
    const config: RequestConfig = {
      resourceName: this.resourceName,
    };

    if (this.isNew) {
      return this.crudService.create(payload, config);
    }
    const id = (payload as any).id; // TODO determinar el ID del modelo. La prop "id" no existe siempre.
    config.pathParams = { id };

    return this.crudService.update(id, payload, config);
  }

  protected async delete(id: any) {
    try {
      this.status.status = 'IDLE';
      const operation = this.createDeleteOperation(id);

      this.status.status = 'PROCESS';
      await firstValueFrom(operation);

      await this.afterDeleteSuccess();
    } catch (e: any) {
      await this.afterDeleteError(e);
    }
  }

  protected createDeleteOperation(id: any): Observable<void> {
    const config: RequestConfig = {
      resourceName: this.resourceName,
    };
    config.pathParams = {id};

    return this.crudService.delete(id, config);
  }

  /**
   * Transforms data model to form value.
   */
  protected mapModelToForm(src: T) {
    return this.mapper.mapModelToForm(src);
  }

  /**
   * Transforms form value to data model.
   */
  protected mapFormToModel(src: F) {
    return this.mapper.mapFormToModel(src);
  }

  /**
   * Runs when the save process executes successfully.
   */
  protected async afterSaveSuccess(result: T) {
    this.srcData = { ...result };

    this.form.markAsPristine();
    this.form.markAsPristine();

    this.activeOperation = undefined;

    this.status.status = 'IDLE';
    //this.notification.show({ message: 'text.other.dataSaved' });

    if (this.redirectAfterSave) {
      await this.router.navigate(this.redirectAfterSavePath, { relativeTo: this.route });
    }
  }

  protected async afterDeleteSuccess() {

    this.form.markAsPristine();
    this.form.markAsPristine();

    this.status.status = 'IDLE';
    this.notification.show({message: 'text.other.dataDelete'});
  }

  /**
   * Runs after the save process fails.
   */
  protected async afterSaveError(e: any) {
    const err = AppError.parse(e);
    /*this.notification.show({
      title: 'text.err.dataSave',
      message: err.error,
    });*/
    this.activeOperation = undefined;
    this.status.status = 'ERROR';
  }

  protected async afterDeleteError(e: any) {
    const err = AppError.parse(e);
    this.notification.show({
      title: 'text.err.dataDelete',
      message: err.error,
    });
    this.status.status = 'ERROR';
  }

  protected getRedirectAfterSaveRoute(): string[] {
    return ['../consulta'];
  }

  protected abstract buildForm(): FormGroup<ControlsOf<F>>;

  /**
   * Whether if the current resource is new.
   */
  get isNew() {
    const possibleNewValues = [0, '0'];
    return possibleNewValues.includes(this.resourceId);
  }

  get srcData() {
    return this._srcData;
  }

  set srcData(value: T | undefined) {
    this._srcData = value;
  }

  get startValue() {
    return this._startValue;
  }

  set startValue(value: F | undefined) {
    this._startValue = value;
  }

  get activeOperation() {
    return this._activeOperation;
  }

  set activeOperation(operation: Observable<T> | undefined) {
    this._activeOperation = operation;
    this.status.status = operation ? 'LOAD' : 'IDLE';
  }

  get hasOngoingOperation() {
    return !!this._activeOperation;
  }

  get headerTitle() {
    return this.isNew ? this._createResourceTitle : this._editResourceTitle;
  }

}
