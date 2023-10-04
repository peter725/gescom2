import { Component, EventEmitter, Input, OnDestroy, Optional, Output, Self } from '@angular/core';
import { ControlValueAccessor, FormControl, NgControl } from '@angular/forms';
import { INCLUDE_ALL_STATES } from '@libs/sdk/common';
import { ReplaySubject } from 'rxjs';


@Component({
  selector: 'tsw-filter-hidden',
  styleUrls: ['./filter-hidden.component.scss'],
  template: `
    <mat-slide-toggle
      [formControl]="ctrl"
      (change)="toggle($event.checked)"
    >{{ 'fields.showHidden' | translate }}</mat-slide-toggle>
  `,
})
export class FilterHiddenComponent implements OnDestroy, ControlValueAccessor {
  readonly ctrl = new FormControl<boolean>(false);

  @Output() changeEv = new EventEmitter<number[] | null>();

  private onChangeFn: ((value: number[] | null) => void) | undefined;
  private onTouchedFn: (() => void) | undefined;

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    @Optional() @Self() private ngControl: NgControl,
  ) {
    if (this.ngControl) this.ngControl.valueAccessor = this;
  }

  @Input()
  set value(value: number[]) {
    this.setCtrlValue(value);
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  onChange(value: number[] | null) {
    if (this.onChangeFn) this.onChangeFn(value);
    this.changeEv.emit(value);
  }

  registerOnChange(fn: (value: number[] | null) => void): void {
    this.onChangeFn = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouchedFn = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    isDisabled
      ? this.ctrl.disable()
      : this.ctrl.enable();
  }

  writeValue(obj: number[] | string[] | null): void {
    this.setCtrlValue(obj);
  }

  toggle(value: boolean) {
    const next = !value
      ? null
      : [...INCLUDE_ALL_STATES];
    this.onChange(next);
  }

  private setCtrlValue(obj: number[] | string[] | null) {
    let next = false;
    if (obj != null) {
      const unique = new Set(obj.map(val => +val));
      next = INCLUDE_ALL_STATES.every(expected => unique.has(expected));
    }
    this.ctrl.patchValue(next, { emitEvent: false });
  }
}
