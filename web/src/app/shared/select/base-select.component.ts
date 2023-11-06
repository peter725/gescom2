import { FocusMonitor, FocusOrigin } from '@angular/cdk/a11y';
import { BooleanInput, coerceBooleanProperty, coerceNumberProperty, NumberInput } from '@angular/cdk/coercion';
import {
  Directive,
  DoCheck,
  ElementRef,
  EventEmitter,
  HostBinding,
  Input,
  OnDestroy,
  OnInit,
  Optional,
  Output,
  Self
} from '@angular/core';
import { ControlValueAccessor, FormGroupDirective, NgControl, NgForm } from '@angular/forms';
import { ErrorStateMatcher, MatOption, mixinDisabled, mixinErrorState } from '@angular/material/core';
import { MatFormFieldControl } from '@angular/material/form-field';
import { CrudImplService, Page, PageReq, ParamRequestConfig, RequestConfig, RequestParams } from '@libs/crud-api';
import { BehaviorSubject, ReplaySubject, Subject, Subscription, take, takeUntil } from 'rxjs';
import { map } from 'rxjs/operators';
import { ComboValue, compareObjects } from './combo';


export class CustomSelectBase {
  constructor(
    public _parentFormGroup: FormGroupDirective,
    public _parentForm: NgForm,
    public _defaultErrorStateMatcher: ErrorStateMatcher,
    public ngControl: NgControl,
    public readonly stateChanges: Subject<void>,
  ) {
  }
}

export const CustomSelectMixin = mixinDisabled(mixinErrorState(CustomSelectBase));

@Directive()
export abstract class CustomSelectBaseComponent<T = ComboValue>
  extends CustomSelectMixin
  implements OnInit, OnDestroy, DoCheck, MatFormFieldControl<T>, ControlValueAccessor {

  @Output() valueSelected = new EventEmitter<T | undefined>();

  @HostBinding() id: string;
  @HostBinding('attr.aria-describedby') userAriaDescribedBy = '';
  @HostBinding('class.floated') shouldLabelFloat = false;

  controlType = 'custom-select';
  /**
   * Whether it should display a loading animation
   */
  animationLoading = false;
  /**
   * Whether it should display a loading animation
   */
  animationError = false;
  /**
   * Monitored element reference
   */
  elementRef: ElementRef | undefined;

  /**
   *
   */
  private _resourceName = '';
  /**
   *
   */
  private _automaticLoad = true;
  /**
   *
   */
  private _disabled = false;
  /**
   *
   */
  private _placeholder = '';
  /**
   *
   */
  private _focused = false;
  /**
   *
   */
  private _multiple = false;
  /**
   *
   */
  private _required = false;
  /**
   * Whether to show an option to clear selected value or not.
   */
  private _showNoneValue = true;
  /**
   * Whether to show an option to reset icon clear the selected value or not.
   */
  private _showResetOption = false;
  /**
   * Whether to load automatically all data or not.
   */
  private _unpaged = true;
  /**
   * Whether to load data on init or only after user has interacted with the component.
   */
  private _lazyLoad = true;
  /**
   *
   */
  private _disableLoadingAnimation = false;
  /**
   * Number of items to load automatically if unpaged.
   */
  private _itemsPerPage = 25;
  /**
   * Currently selected value.
   */
  private _activeValue: T | null = null;
  /**
   * Source data
   */
  private _dataSrc = new BehaviorSubject<T[]>([]);
  /**
   * Source page for the existing data source.
   */
  private _dataPage = Page.unpaged<T>();
  /**
   * Parameters used to load the Data Source.
   */
  private _dataSrcParams: Partial<ParamRequestConfig> = {
    pathParams: {},
    queryParams: {},
  };
  /**
   * Aplicable sort configuration
   */
  private _dataSort: string[] = [];
  /**
   * Value used to search
   */
  private _searchValue: string | undefined;
  /**
   * Path to display for the provided object.
   * Default is name, as is the most common property.
   */
  private _displayPath = 'name';

  private onChangeFn: ((value: T | undefined) => void) | undefined;
  private onTouchFn: (() => void) | undefined;

  private hasLoadedOnce = false;

  private activeDataFetch: Subscription | undefined;

  protected readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    protected crudService: CrudImplService<T>,
    protected focusMonitor: FocusMonitor,
    @Optional() @Self() ngControl: NgControl,
    @Optional() parentForm: NgForm,
    @Optional() parentFormGroup: FormGroupDirective,
    errorStateMatcher: ErrorStateMatcher,
  ) {
    super(parentFormGroup, parentForm, errorStateMatcher, ngControl, new Subject<void>());
    this.id = this.getComponentId();
    if (this.ngControl != null) this.ngControl.valueAccessor = this;
  }

  /**
   * Defines the resource to load. Enables automatic data loading.
   * Contradicts {@link dataSrc}
   */
  @Input()
  set resourceName(resource: string) {
    console.log("entro aqui primero");
    this._resourceName = resource;
    this._automaticLoad = true;
  }

  get resourceName() {
    return this._resourceName;
  }

  /**
   * Configures params required to automatically load data.
   */
  @Input()
  set searchQueryParams(params: RequestParams) {
    this._dataSrcParams.queryParams = params;
    if (this.hasLoadedOnce) this.loadData();
  }

  @Input()
  set searchPathParams(params: RequestParams) {
    this._dataSrcParams.pathParams = params;
    if (this.hasLoadedOnce) this.loadData();
  }

  /**
   * Alternative data source value controlled manually. Disables automatic data loading.
   * Contradicts {@link resourceName}
   */
  @Input()
  set dataSrc(ds: T[]) {
    this._dataSrc.next(ds);
    this.resourceName = '';
    this._automaticLoad = false;
  }

  get dataSrc() {
    return this._dataSrc.value;
  }

  /**
   *
   */
  @Input()
  set value(value: T | null) {
    this._activeValue = value;
    this.stateChanges.next();
  }

  get value() {
    return this._activeValue;
  }

  @Input()
  override set disabled(value: any) {
    this._disabled = coerceBooleanProperty(value);
    this.stateChanges.next();
  }

  override get disabled() {
    return this._disabled;
  }

  @Input()
  set disable(value: any) {
    this.disabled = value;
  }

  @Input()
  set placeholder(value: string) {
    this._placeholder = value;
    this.stateChanges.next();
  }

  get placeholder() {
    return this._placeholder;
  }

  @Input() set focused(value: any) {
    this._focused = coerceBooleanProperty(value);
  }

  get focused() {
    return this._focused;
  }

  @Input() set multiple(value: any) {
    this._multiple = coerceBooleanProperty(value);
  }

  get multiple() {
    return this._multiple;
  }

  @Input() set required(value: any) {
    this._required = coerceBooleanProperty(value);
  }

  get required() {
    return this._required;
  }

  @Input() set showNoneValue(value: BooleanInput) {
    this._showNoneValue = coerceBooleanProperty(value);
  }

  get showNoneValue() {
    return this._showNoneValue;
  }

  @Input() set showResetOption(value: BooleanInput) {
    this._showResetOption = coerceBooleanProperty(value);
  }

  get showResetOption() {
    return this._showResetOption;
  }

  @Input()
  set unpaged(value: BooleanInput) {
    const next = coerceBooleanProperty(value);
    if (next !== this.unpaged) this._unpaged = next;
  }

  get unpaged() {
    return this._unpaged;
  }

  @Input()
  set sort(sort: string | string[]) {
    this._dataSort = Array.isArray(sort) ? sort : [sort];
  }

  get sort(): string[] {
    return this._dataSort;
  }

  @Input()
  set lazyLoad(value: BooleanInput) {
    this._lazyLoad = coerceBooleanProperty(value);
  }

  get lazyLoad() {
    return this._lazyLoad;
  }

  @Input()
  set itemsPerPage(value: NumberInput) {
    const next = coerceNumberProperty(value);
    if (next !== this.itemsPerPage) this._itemsPerPage = next;
    this.unpaged = false;
  }

  get itemsPerPage(): number {
    return this._itemsPerPage;
  }

  @Input()
  set disableLoadingAnimation(value: BooleanInput) {
    this._disableLoadingAnimation = coerceBooleanProperty(value);
  }

  get disableLoadingAnimation() {
    return this._disableLoadingAnimation;
  }

  @Input()
  set displayPath(path: string) {
    this._displayPath = path;
  }

  get displayPath() {
    return this._displayPath;
  }

  get hasMorePages() {
    return !this.unpaged && !this._dataPage.details.last;
  }

  /**
   *
   */
  @Input()
  compareWith: (a: ComboValue , b: ComboValue) => boolean = compareObjects;

  /**
   *
   */
  @Input()
  sortComparator: (a: MatOption, b: MatOption, options: MatOption[]) => number = (a: MatOption, b: MatOption, options: MatOption[]) => options.indexOf(a) - options.indexOf(b);

  /**
   * Executes operations like sort, filter, etc on {@link dataSrc} before displaying
   */
  @Input()
  displayDataMapper: (values: T[]) => T[] | any[] = (values: T[]) => values;

  ngOnInit(): void {
    if (!this.lazyLoad) this.loadData();
    this.registerFocusMonitors();
  }

  ngDoCheck(): void {
    if (this.ngControl) {
      this.updateErrorState();
    }
  }

  ngOnDestroy(): void {
    this._dataSrc.complete();
    this.stateChanges.complete();
    this.destroyed$.next(true);
  }

  registerOnChange(fn: any): void {
    this.onChangeFn = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouchFn = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
    this.stateChanges.next();
  }

  writeValue(obj: T | undefined | null): void {
    this.value = obj != null ? obj : null;
    // If src is lazy loaded and there are no values, we add a
    // temporary list of values until the other ones are loaded
    if (!this.dataSrc.length && this.lazyLoad && obj != null) {
      this._dataSrc.next(Array.isArray(obj) ? obj : [obj]);
    }
    this.updateShouldLabelFloat();
  }

  setDescribedByIds(ids: string[]): void {
    this.userAriaDescribedBy = ids.join(' ');
  }

  onContainerClick(): void {
    this.openSelectionOptions();
  }

  onChange(value: T | undefined) {
    if (this.onChangeFn) this.onChangeFn(value);
    this.valueSelected.emit(value);
    this.updateShouldLabelFloat();
  }

  onTouch() {
    if (this.onTouchFn) this.onTouchFn();
  }

  resetValue() {
    this.value = null;
    this.onChange(undefined);
    this.stateChanges.next();
  }

  shouldNotLoadData() {
    return !this.automaticLoad || !this.resourceName;
  }

  search(value: string | undefined) {
    this._searchValue = value;
    // Reset current data list and pagination details.
    this._dataPage = Page.unpaged();
    this._dataSrc.next([]);
    this.loadData();
  }

  openSelectionOptions(): void {
    if (this.elementRef) this.focusMonitor.focusVia(this.elementRef, 'program');
  }

  loadNext() {
    this.loadData();
  }

  get displayDataSrc() {
    return this._dataSrc.asObservable().pipe(
      map(values => this.displayDataMapper(values))
    );
  }

  get empty() {
    return !this._activeValue;
  }

  get automaticLoad() {
    return this._automaticLoad;
  }

  get searchValue() {
    return this._searchValue;
  }

  abstract getComponentId(): string;

  protected registerFocusMonitors() {
    if (this.elementRef) {
      this.focusMonitor.monitor(this.elementRef).pipe(take(1)).subscribe(() => this.onFirstFocus());
      this.focusMonitor.monitor(this.elementRef).pipe(takeUntil(this.destroyed$)).subscribe(origin => this.onFocus(origin));
    }
  }

  protected updateShouldLabelFloat() {
    const next = this.disabled
      ? false
      : (this.focused || !this.empty);
    if (this.shouldLabelFloat !== next) this.shouldLabelFloat = next;
  }

  protected loadData() {
    if (this.shouldNotLoadData()) {
      return;
    }

    // if (!this.hasLoadedOnce && this.lazyLoad) {
    //   return;
    // }

    this.hasLoadedOnce = true;

    if (this.activeDataFetch) {
      this.activeDataFetch.unsubscribe();
      this.activeDataFetch = undefined;
    }

    this.playLoadingAnimation(true);
    this.activeDataFetch = this.fetchData().subscribe({
      next: page => this.afterDataLoaded(page),
      error: err => this.afterDataLoadErr(err),
    });
  }

  protected fetchData() {
    const config = this.buildRequestConfig();
    return this.crudService.findAll(config);
  }

  protected buildRequestConfig() {
    const next = this._dataPage.next();
    const pageReq: PageReq = (this.unpaged)
      ? { sort: this.sort, unpaged: true, }
      : {
        sort: this.sort,
        size: this.itemsPerPage,
        page: ('page' in next) ? next.page : 0,
      };

    const queryParams: RequestParams = {
      ...this._dataSrcParams?.queryParams,
      search: this.searchValue ? `%${this.searchValue}%` : '',
    };

    const pathParams = {
      ...this._dataSrcParams?.pathParams,
    };

    const config: RequestConfig = {
      resourceName: this.resourceName,
      pageReq,
      queryParams,
      pathParams,
    };
    return config;
  }

  protected afterDataLoaded(result: Page<T>) {
    this._dataPage = result;
    const next = this.unpaged ? [...result.content] : [...this.dataSrc, ...result.content];
    this._dataSrc.next(next);
    this.playLoadingAnimation(false);
  }

  protected afterDataLoadErr(err: unknown) {
    console.error(err);
    this.dataSrc = [];
    this.playLoadingAnimation(false);
    this.playErrorAnimation();
  }

  protected onFirstFocus() {
    this.onTouch();
    if (this.lazyLoad) this.loadData();
  }

  protected onFocus(origin: FocusOrigin) {
    this.focused = !!origin;
    this.stateChanges.next();
    this.updateShouldLabelFloat();
  }

  protected playLoadingAnimation(value: boolean | null) {
    let next: boolean;
    if (this.disableLoadingAnimation) {
      next = false;
    } else {
      next = typeof value === 'boolean' ? value : !this.animationLoading;
    }

    // avoid unnecessary value update
    if (next !== this.animationLoading) this.animationLoading = next;
  }

  protected playErrorAnimation() {
    this.animationError = true;
    setTimeout(() => this.animationError = false, 1000);
  }

}
