import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { VTulsaFieldModule } from '@tulsa/libs/sdk/field-module';
import { SampleDataValue, TulsaSampleData, TulsaSampleResult } from '@tulsa/libs/sdk/sample';


export type SampleDataGroup = Record<string, FormControl<SampleDataValue>>;

/**
 * Formato tomado por el formulario de recogida de datos.
 * El objetivo de este formulario es recoger y manipular los datos
 * sin realizar cambios adicionales en el resto de datos de la muestra.
 */
export interface SampleFormData {
  data: FormGroup<SampleDataGroup>;
  results: FormArray<FormGroup<SampleFormResult>>;
}

export interface SampleFormResult {
  id: FormControl<number | null>;
  // Clave única identificativa del resultado dentro del formulario
  groupKey: FormControl<string>;
  sampleId: FormControl<number | null>;
  data: FormGroup<SampleDataGroup>;
}

/**
 * Estructura base para la representación visual del formulario
 * de muestras.
 */
/*interface SampleUIBase {
  code: string;
  label: string;
  order: number;
  hidden: boolean;
  cssClass: string;
}*/

/**
 * Agrupación de
 */
/*export interface SampleUIGroupBase<T> extends SampleUIBase {
  items: T[];
}*/

/**
 * Estructura para representar el campo individual del formulario
 */
/*export interface SampleUIDataField extends VTulsaFieldModule {
  /!**
   * Nombre del form control correspondiente en el formulario
   * Este valor será formateado mientras que el original mantiene
   * su valor.
   *!/
  controlName: string;
  order: number;
}*/

/**
 * Agrupación de los elementos por su grupo base
 */
/*export interface SampleUISection extends SampleUIBase {
  type: 'data' | 'result';
  items: SampleUIDataRow[];
}*/

// No utilizar hasta disponer de las reglas de asociación
/*export type SampleUIGroupedSection = SampleUIGroupBase<SampleUIDataGroup>;*/

/**
 * Agrupación de los elementos en filas ordenables
 * No utilizar hasta las reglas de asociación
 * */
/*export type SampleUIDataGroup = SampleUIGroupBase<SampleUIDataRow>;*/

/**
 * Agrupación de los elementos ordenados
 */
/*export type SampleUIDataRow = SampleUIGroupBase<SampleUIDataField>;*/
