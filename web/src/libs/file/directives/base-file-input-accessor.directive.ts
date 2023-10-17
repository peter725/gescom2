import { coerceBooleanProperty } from '@angular/cdk/coercion';
import { Directive, ElementRef, HostListener, Input, Optional, Renderer2, Self } from '@angular/core';
import { ControlValueAccessor, NgControl, ValidationErrors } from '@angular/forms';

@Directive()
export abstract class BaseFileInputAccessorDirective<T> implements ControlValueAccessor {
  protected _disabled = false;
  protected _multiple = false;
  private _accept = '';

  protected _onChangeFn: (value: T | null) => void = () => undefined;
  protected _onTouchedFn: () => void = () => undefined;


  constructor(
    @Self() @Optional() protected ngControl: NgControl,
    protected element: ElementRef<HTMLInputElement>,
    protected render: Renderer2,
  ) {
    if (ngControl) {
      ngControl.valueAccessor = this;
    }
  }

  @Input()
  set accept(value: string) {
    this._accept = value;
  }

  get accept() {
    return this._accept;
  }

  @Input()
  set multiple(value: unknown) {
    this._multiple = coerceBooleanProperty(value);
  }

  @Input()
  set disabled(value: unknown) {
    this._disabled = coerceBooleanProperty(value);
  }


  @HostListener('change', ['$event.target.files'])
  handleChangeInput(list: FileList) {
    try {
      this.verifyInput(list);
      this.handleInput(list);
    } catch (e) {
      // Ignored
    }
  }

  registerOnChange(fn: (value: T | null) => void): void {
    this._onChangeFn = fn;
  }

  registerOnTouched(fn: () => void): void {
    this._onTouchedFn = fn;
  }

  setDisabledState(disabled: boolean): void {
    this._disabled = disabled;
  }

  writeValue(): void {
    // Ignored.
  }

  protected setErrors(errors: ValidationErrors) {
    if (!this.ngControl?.control) {
      return;
    }

    this.ngControl.control.setErrors(errors);
  }

  protected verifyInput(list: FileList) {
    if (this._disabled) {
      const message = `Input is disabled`;
      console.error(message);
      this.setErrors({ disabled: true });
      throw new Error(message);
    }

    if (this.accept) {
      const allMatch = Array.from(list).every(file => this.accept.includes(file.type));
      if (!allMatch) {
        const message = `Input files don't match required file formats [${ this.accept }]`;
        console.error(message);
        this.setErrors({
          invalidFileFormat: {
            requested: this.accept,
          },
        });
        throw new Error(message);
      }
    }
  }

  protected abstract handleInput(list: FileList): void;
}
