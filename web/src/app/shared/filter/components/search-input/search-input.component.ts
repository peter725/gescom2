import { FocusMonitor } from '@angular/cdk/a11y';
import {
  Component,
  DoCheck,
  ElementRef,
  HostBinding,
  Input,
  OnDestroy,
  OnInit,
  Optional,
  Self,
  ViewChild,
} from '@angular/core';
import { ControlValueAccessor, FormBuilder, FormGroup, FormGroupDirective, NgControl, NgForm, } from '@angular/forms';
import { ErrorStateMatcher, mixinDisabled, mixinErrorState, } from '@angular/material/core';
import { MatFormFieldControl } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { ControlsOf, OPERATION_SEPARATOR, OperationMode } from '@libs/commons';
import { distinctUntilChanged, ReplaySubject, Subject, takeUntil } from 'rxjs';
import { take } from 'rxjs/operators';


const AVAILABLE_OPERATIONS: {
  text: { code: OperationMode, text: string }[],
  number: { code: OperationMode, text: string }[],
} = {
  text: [
    { code: OperationMode.CONTAINS, text: 'text.searchModes.contains' },
    { code: OperationMode.EQUALS, text: 'text.searchModes.equals' },
    { code: OperationMode.STARTS_WITH, text: 'text.searchModes.startsWith' },
    { code: OperationMode.ENDS_WITH, text: 'text.searchModes.endsWith' },
  ],
  number: [
    { code: OperationMode.EQUALS, text: 'text.searchModes.contains' },
    { code: OperationMode.LESSER_OR_EQUAL, text: 'text.searchModes.lesserOrEqual' },
    { code: OperationMode.GREATER_OR_EQUAL, text: 'text.searchModes.greaterOrEqual' },
  ]
};

export interface FormFieldValue {
  query: string;
  operation: OperationMode;
}

export class CustomSearch {
  constructor(
    public _parentFormGroup: FormGroupDirective,
    public _parentForm: NgForm,
    public _defaultErrorStateMatcher: ErrorStateMatcher,
    public ngControl: NgControl,
    public readonly stateChanges: Subject<void>,
  ) {
  }
}

export const SearchBaseMixin = mixinDisabled(mixinErrorState(CustomSearch));


@Component({
  selector: 'tsw-search-input',
  templateUrl: './search-input.component.html',
  styleUrls: ['./search-input.component.scss'],
  providers: [
    {
      provide: MatFormFieldControl,
      useExisting: SearchInputComponent,
    },
  ],
})
export class SearchInputComponent
  extends SearchBaseMixin
  implements OnInit, OnDestroy, MatFormFieldControl<FormFieldValue>, ControlValueAccessor, DoCheck {

  static nextId = 0;

  @ViewChild(MatInput, { read: ElementRef, static: true }) input: ElementRef | undefined;

  @HostBinding() id = `custom-form-field-id-${ SearchInputComponent.nextId++ }`;
  @HostBinding('attr.aria-describedby') describedBy = '';

  @Input() required = false;
  @Input() step = '';

  focused = false;
  controlType = 'custom-form-field';

  form: FormGroup<ControlsOf<FormFieldValue>>;
  operations: { code: OperationMode, text: string }[] = [];

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  private _type: 'text' | 'number' = 'text';
  private _placeholder = '';

  private onChangeFn: ((value: string | undefined) => void) | undefined;
  private onTouchFn: (() => void) | undefined;

  constructor(
    private focusMonitor: FocusMonitor,
    private fb: FormBuilder,
    @Optional() parentFormGroup: FormGroupDirective,
    @Optional() parentForm: NgForm,
    @Optional() @Self() ngControl: NgControl,
    errorStateMatcher: ErrorStateMatcher,
  ) {
    super(parentFormGroup, parentForm, errorStateMatcher, ngControl, new Subject<void>());
    if (this.ngControl != null) {
      this.ngControl.valueAccessor = this;
    }
    this.form = this.fb.group<ControlsOf<FormFieldValue>>({
      query: fb.control(null),
      operation: fb.control(OperationMode.CONTAINS),
    });
    this.updateOperations();
  }

  @Input()
  set value(value: FormFieldValue) {
    this.form.patchValue(value);
    this.stateChanges.next();
  }

  get value(): FormFieldValue {
    return this.form.value as any;
  }

  @Input()
  set placeholder(value: string) {
    this._placeholder = value;
    this.stateChanges.next();
  }

  get placeholder() {
    return this._placeholder;
  }

  @Input()
  set type(type: 'text' | 'number') {
    this._type = type;
    if (type === this._type) return;
    this.updateOperations();
  }

  get type() {
    return this._type;
  }

  @HostBinding('class.floated')
  get shouldLabelFloat(): boolean {
    return true;
  }

  get empty(): boolean {
    return !this.value.query && !this.value.operation;
  }

  writeValue(obj: FormFieldValue | string): void {
    if (!obj) {
      this.form.controls.query.reset(null, { emitEvent: false });
      return;
    }

    let next: FormFieldValue;
    if (typeof obj === 'string') {
      const [query, operation] = obj.split(OPERATION_SEPARATOR);
      next = {
        query: query.trim(),
        operation: operation.trim() as OperationMode
      };
    } else {
      next = obj;
    }
    // write external value
    this.value = next;
  }

  registerOnChange(fn: any): void {
    this.onChangeFn = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouchFn = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
    this.form.disable();
    this.stateChanges.next();
  }

  setDescribedByIds(ids: string[]): void {
    this.describedBy = ids.join(' ');
  }

  onContainerClick(): void {
    if (!this.input) return;
    this.focusMonitor.focusVia(this.input, 'program');
  }

  ngOnInit(): void {
    if (!this.input) {
      setTimeout(() => this.ngOnInit(), 1000);
      return;
    }
    this.focusMonitor.monitor(this.input).subscribe((focused) => {
      this.focused = !!focused;
      this.stateChanges.next();
    });
    this.focusMonitor
      .monitor(this.input)
      .pipe(take(1))
      .subscribe(() => this.onTouch());
    this.form.valueChanges.pipe(
      takeUntil(this.destroyed$),
      distinctUntilChanged(),
    ).subscribe(
      value => this.onChange(value as FormFieldValue)
    );
  }

  onChange(value: FormFieldValue | undefined) {
    if (!this.onChangeFn) return;

    let next: string | undefined;
    if (value && !!value.query?.trim()) {
      next = value.query + OPERATION_SEPARATOR + value.operation;
    }
    this.onChangeFn(next);
  }

  onTouch() {
    if (this.onTouchFn) this.onTouchFn();
  }

  ngDoCheck() {
    if (this.ngControl) {
      this.updateErrorState();
    }
  }

  ngOnDestroy() {
    if (this.input) this.focusMonitor.stopMonitoring(this.input);
    this.stateChanges.complete();
    this.destroyed$.next(true);
  }

  private updateOperations() {
    this.operations = [...AVAILABLE_OPERATIONS[this.type]];
  }

}
