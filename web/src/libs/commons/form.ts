import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Observable, of } from 'rxjs';


export type ControlsOf<T extends Record<string, any>> = {
  [K in keyof T]: T[K] extends Record<any, any>
    ? T[K] extends (infer U)[]
      ? (
        U extends Record<any, any>
          ? FormArray<FormGroup<ControlsOf<U>>>
          : FormArray<FormControl<U>>
        )
      : FormGroup<ControlsOf<T[K]>>
    : FormControl<T[K] | null>
}

/**
 * Mapper class used to transform any data model (T) to a valid form model (F)
 */
export abstract class FormMapper<T, F = T> {
  abstract mapFormToModel(src: F): Observable<T>;

  abstract mapModelToForm(src: T): Observable<F>;
}

/**
 * Mapper for forms using the same model as the form model
 */
export class SameTypeFormMapper<T> extends FormMapper<T, T> {
  mapFormToModel(src: T): Observable<T> {
    return of({ ...src });
  }

  mapModelToForm(src: T): Observable<T> {
    return of({ ...src });
  }
}
