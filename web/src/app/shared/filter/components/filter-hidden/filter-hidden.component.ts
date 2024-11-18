import { Component, EventEmitter, Input, OnDestroy, OnInit, Optional, Output, Self } from '@angular/core';
import { ControlValueAccessor, FormControl, NgControl } from '@angular/forms';
import { INCLUDE_ALL_STATES } from '@libs/sdk/common';
import { ReplaySubject, Subscription } from 'rxjs';
import { FilterService } from '@base/shared/filter';


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
export class FilterHiddenComponent implements OnInit, OnDestroy, ControlValueAccessor {
  readonly ctrl = new FormControl<boolean>(false);

  @Output() changeEv = new EventEmitter<number[] | null>();

  private subscription: Subscription | undefined;
  private onChangeFn: ((value: number[] | null) => void) | undefined;
  private onTouchedFn: (() => void) | undefined;

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    @Optional() @Self() private ngControl: NgControl,
    private filterService: FilterService
  ) {
    if (this.ngControl) this.ngControl.valueAccessor = this;
  }

  ngOnInit(): void {
    // Suscribirse al BehaviorSubject para recibir actualizaciones dinámicas
    this.subscription = this.filterService.showDeleted$.subscribe(value => {
      this.ctrl.setValue(value);  // Actualiza el mat-slide-toggle con el valor del servicio
    });
  }

  @Input()
  set value(value: number[]) {
    this.setCtrlValue(value);
  }

  toggleShowDeleted(value: boolean) {
    this.filterService.setShowDeleted(value);
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

    this.toggleShowDeleted(value);
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
