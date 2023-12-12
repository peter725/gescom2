import { AbstractControl, ValidationErrors } from '@angular/forms';
import { CIF_TEST, NIF_TEST, validCIF, validNIF } from './nif.validator';


export class CustomValidators {

  static nif(control: AbstractControl<string>): ValidationErrors | null {
    if (!control || !control.value) {
      return null;
    }

    const value = control.value.toUpperCase().replace(/[_\W\s]+/g, '');

    if (NIF_TEST.test(value)) {
      console.log("entra a validar el nif")
      // Value is current NIF format (NIF/DNI/NIE)
      return validNIF(value);
    } else if (CIF_TEST.test(value)) {
      // Value is old CIF format (CIF/ESP)
      return validCIF(value);
    }

    return {
      invalidNif: true,
    };
  }

  static allowedName(control: AbstractControl<string>): ValidationErrors | null {
    if (!control || !control.value) {
      return null;
    }

    const acceptedChars = /^[a-zA-Z\u00C0-\u017F\s]+$/;
    if (acceptedChars.test(control.value.trim())) {
      return null;
    }
    return {
      disallowedName: true,
    }
  }
}

