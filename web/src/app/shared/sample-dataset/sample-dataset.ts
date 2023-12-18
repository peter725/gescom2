import { TrackByFunction } from '@angular/core';
import { FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ValidationError } from '@nrwl/angular/src/generators/ng-add/utilities/types';
import { FieldElementType } from '@tulsa/libs/sdk/field-module';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { BehaviorSubject, Subject, Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import {
  DatasetColumnOpts,
  DatasetContainerColumnOpts,
  DatasetContainerGroupOpts,
  DatasetContainerOpts,
  DatasetContainerType,
  DatasetContainerUI,
  DatasetFormGroup,
  DatasetFormValue,
  DatasetHideChangeEv,
} from './sample-dataset.model';


export class DatasetCoordinates {
  static readonly SEPARATOR = '#';

  static construct(type: string, coordinates: Array<number | string>) {
    return [type, ...coordinates].join(this.SEPARATOR);
  }

  static destruct(position: string) {
    return position.split(this.SEPARATOR);
  }
}

export abstract class DatasetBaseContainerUI<T extends DatasetContainerUI> implements DatasetContainerUI {
  order: number;
  index: number;
  position: string;
  label: string;

  protected errors: ValidationErrors | null = null;

  // protected _items: T[] = [];
  protected _items: Record<string, T> = {};
  private _visibleItems$ = new BehaviorSubject<T[]>([]);

  private _hidden = false;
  private _onHidden = new Subject<DatasetHideChangeEv>();

  private _onHiddenSubscriptions = new Subscription();

  constructor(opts: DatasetContainerOpts<T>) {
    this.order = opts.order || 0;
    this.index = opts.index || 0;
    this.position = opts.position || '';
    this.label = opts.label || '';
    if (opts.items) this.setItems(opts.items);
  }

  get onHidden() {
    return this._onHidden.asObservable();
  }

  get isHidden() {
    return this._hidden;
  }

  get items() {
    return Object.values(this._items).sort((a, b) => a.order - b.order);
    // return [...this._items];
  }

  get visibleItems$() {
    return this._visibleItems$.asObservable();
  }

  setError(error: ValidationError) {
  }

  setItems(items: T[]) {
    this._items = this.mapItemsToRecord(items);
    this.updateVisible();
    this.monitorVisibilityChanges();
  }

  pushItem(item: T) {
    // Cada implementación debe sobrescribir este método

    this.updateVisible();
    this.monitorVisibilityChanges();
  }

  destroy() {
    this._onHiddenSubscriptions.unsubscribe();
    this._onHiddenSubscriptions = new Subscription();
  }

  update(opts: DatasetColumnOpts) {
    if (!opts.targetPosition.startsWith(this.position)) {
      return;
    }
    if (opts.targetPosition === this.position) {
      return;
    }
    const coordsLocal = DatasetCoordinates.destruct(this.position);
    const nextIdx = coordsLocal.length;
    const parts = DatasetCoordinates.destruct(opts.targetPosition);
    const targetIdx = parts[nextIdx];
    const item = this._items[targetIdx];

    if (item) item.update(opts);
  }

  protected mapItemsToRecord(items: T[]): Record<string, T> {
    return items.reduce<Record<string, T>>((acc, item) => {
      if (!acc[item.order]) acc[item.order] = item;
      return acc;
    }, {});
  }

  protected monitorVisibilityChanges() {
    this.destroy();
    this.items.forEach(item => {
      const sub = item.onHidden.subscribe(ev => this.onHiddenChild(ev));
      this._onHiddenSubscriptions.add(sub);
    });
  }

  protected setHidden(value: boolean) {
    this._hidden = value;
    this._onHidden.next({
      position: this.position,
      hidden: value,
    });
  }

  protected updateVisible() {
    const list = [...this.items].filter(item => !item.isHidden);
    this._visibleItems$.next(list);
    if (!list.length) {
      this.setHidden(true);
    }
  }

  protected onHiddenChild(ev: DatasetHideChangeEv) {
    this.updateVisible();
    const hasItems = this._visibleItems$.value.length > 0;

    if (hasItems && this._hidden) {
      this.setHidden(false);
    } else if (!hasItems) {
      this.setHidden(true);
    }
  }

}

export const datasetTrackByFn: TrackByFunction<DatasetBaseContainerUI<any>> = (_, item) => item.position;

export class DatasetGroupContainerUI extends DatasetBaseContainerUI<DatasetRowContainerUI> {
  formGroup: FormGroup<DatasetFormGroup>;
  containerType = DatasetContainerType.COMMON;

  constructor(opts: DatasetContainerGroupOpts<DatasetRowContainerUI>) {
    super(opts);
    this.formGroup = opts.formGroup;
    this.containerType = opts.containerType;
  }

  override pushItem(item: DatasetRowContainerUI) {
    this._items[item.order] = item;
    super.pushItem(item);
  }

}

export class DatasetRowContainerUI extends DatasetBaseContainerUI<DatasetColumnUI> {
  override pushItem(item: DatasetColumnUI) {
    this._items[item.elementName] = item;
    super.pushItem(item);
  }

  protected override mapItemsToRecord(items: DatasetColumnUI[]): Record<string, DatasetColumnUI> {
    return items.reduce<Record<string, DatasetColumnUI>>((acc, item) => {
      if (!acc[item.elementName]) acc[item.elementName] = item;
      return acc;
    }, {});
  }
}

export class DatasetColumnUI<T extends DatasetFormValue = DatasetFormValue> implements DatasetContainerUI {
  readonly formCtrl: FormControl<DatasetFormValue>;
  readonly ctrlName: string;
  readonly elementName: string;
  readonly elementType: FieldElementType;

  label: string;
  description: string;
  position: string;
  order: number;
  index: number;

  protected errors: ValidationErrors | null = null;

  private _hidden = false;
  private _onHidden = new Subject<DatasetHideChangeEv>();

  private _comboMultiselect = false;
  private _comboValues = new BehaviorSubject<TulsaFieldModuleTerm[]>([]);

  private readonly _catalogueId: number | undefined;
  private readonly _hierarchyId: number | undefined;

  constructor(opts: DatasetContainerColumnOpts<T>) {
    this.elementName = opts.elementName;
    this.elementType = opts.elementType;
    this.ctrlName = DatasetColumnUI.constructCtrlName(opts.elementName);
    this.formCtrl = opts.formCtrl;

    this.order = opts.order || 0;
    this.index = opts.index || 0;
    this.label = opts.label || '';
    this.description = opts.description || '';
    this.position = opts.position || '';

    this._hidden = opts.hidden || false;
    this._comboMultiselect = opts.comboMultiselect || false;
    if (opts.comboValues) this.setComboValues(opts.comboValues);

    this._catalogueId = opts.catalogueId;
    this._hierarchyId = opts.hierarchyId;
  }

  static constructCtrlName(value: string) {
    return value.replace('.', '__');
  }

  static destructCtrlName(value: string) {
    return value.replace('__', '.');
  }

  get isHidden() {
    return this._hidden;
  }

  get isHidden$() {
    return this._onHidden.pipe(map(() => this._hidden));
  }

  get onHidden() {
    return this._onHidden.asObservable();
  }

  get isMultiselect() {
    return this._comboMultiselect;
  }

  get comboValues$() {
    return this._comboValues.asObservable();
  }

  get comboValues() {
    return [...this._comboValues.value];
  }

  get catalogueId() {
    return this._catalogueId;
  }

  get hierarchyId() {
    return this._hierarchyId;
  }

  setHidden(value: boolean) {
    if (value === this._hidden) return;

    this._hidden = value;
    this._onHidden.next({
      position: this.position,
      hidden: value,
    });
  }

  setRequired(value: boolean) {
    value
      ? this.formCtrl.addValidators(Validators.required)
      : this.formCtrl.removeValidators(Validators.required);
  }

  setComboValues(values: TulsaFieldModuleTerm[]) {
    this._comboValues.next(values);
  }

  destroy(): void {
  }

  setError(error: ValidationError): void {
  }

  update(opts: DatasetColumnOpts) {
    if (opts.targetPosition !== this.position) {
      return;
    }

    if (opts.hidden != null) this.setHidden(opts.hidden);

    if (opts.required != null) this.setRequired(opts.required);

    if (opts.comboMultiselect != null) this._comboMultiselect = opts.comboMultiselect;

    if (opts.comboOptions != null) this.setComboValues(opts.comboOptions);
  }

}
