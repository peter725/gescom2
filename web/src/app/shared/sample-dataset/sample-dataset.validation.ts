import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';

export class SampleDatasetValidation {
  static validateColumn(): AsyncValidatorFn {
    return (ctrl: AbstractControl) => new Promise<ValidationErrors | null>(resolve => {
      resolve(null);
    });
  }

  static validateGroup(): AsyncValidatorFn {
    return (ctrl: AbstractControl) => new Promise<ValidationErrors | null>(resolve => {
      resolve(null);
    });
  }

  static validateComplete(): AsyncValidatorFn {
    return (ctrl: AbstractControl) => new Promise<ValidationErrors | null>(resolve => {
      resolve(null);
    });
  }
}
