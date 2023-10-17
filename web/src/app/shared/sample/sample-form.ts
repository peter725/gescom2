import { FormArray, FormGroup } from '@angular/forms';
import { ColumnDef, ColumnSource } from '@tulsa/app/shared/collections';
import { ComponentStatus } from '@tulsa/libs/commons';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { TulsaSample } from '@tulsa/libs/sdk/sample';
import { Observable } from 'rxjs';
import { SampleFormData } from './models';
import { SampleUi, SampleUiResultSection } from './sample-ui';


export abstract class SampleForm {
  readonly process = new ComponentStatus('LOAD');

  protected _ui = new SampleUi([]);

  protected _srcForm = new FormGroup<SampleFormData>({
    data: new FormGroup({}),
    results: new FormArray<FormGroup>([]),
  });

  protected _resultColumns: ColumnSource | undefined;

  protected _comboValues: Record<string, TulsaFieldModuleTerm[]> = {};

  protected constructor(
    protected readonly _sample: Readonly<TulsaSample>
  ) {
  }

  /**
   * Returns the current UI definition
   */
  get ui() {
    // return this._uiStore.asObservable();
    return this._ui;
  }

  /**
   * Returns the current form definition
   */
  get form() {
    return this._srcForm;
  }

  get resultColumns$(): Observable<ColumnDef[]> {
    if (!this._resultColumns) throw new Error('Result columns object is not initialized.');
    return this._resultColumns.columnDefs$;
  }

  get resultColumns() {
    return this._resultColumns;
  }

  get comboValues() {
    return this._comboValues;
  }

  get sample() {
    return this._sample;
  }

  abstract getValue(): Partial<TulsaSample>;

  abstract addResult(section: SampleUiResultSection, numOfResults?: number): unknown;

  abstract removeResult(index: number): unknown;
}

// Formulario de samples que no permite realizar cambios
export class LockedSampleForm extends SampleForm {
  constructor(
    // Datos existentes del sample. Desconocemos sus valores
    sample: Readonly<TulsaSample>,
    // En caso de editar solamente nos hacen falta los nombres de los campos que existen
    // si el sample no ha sido cerrado, debemos actualizar los campos del formulario
    // private readonly fields: TulsaField[],
    // Reglas de validación que aplican
    // private validationRules: unknown[],
    // Reglas de asociación que aplican
    // private associationRules: unknown[],
  ) {
    super(sample);
  }

  // @ts-ignore
  getValue(): Partial<TulsaSample> {
    return this.sample;
  }

  addResult(section: SampleUiResultSection): unknown {
    return undefined;
  }

  removeResult(index: number): unknown {
    return undefined;
  }

}
