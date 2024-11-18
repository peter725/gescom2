import { AppQuerySource } from '../commons';
import { SimpleModel, StatefulModel, TranslatedModel } from './common';
import { FieldModuleTerm } from './field-module-term';
import { Module } from './module';
import { SampleSeason } from './sample-season';


/**
 * Estructura de datos de la muestra. Esta se compone de una combinación
 * de datos comunes y una lista de resultados compuestos por datos.
 */
export interface Sample extends SimpleModel, StatefulModel {
  scopeCode: string;
  pubCode: string;
  sampleState: SampleState;
  module: Module;
  season: SampleSeason;
  /**
   * Listado de datos comunes que componen la muestra.
   */
  data: SampleData[];
  /**
   * Listado de resultados con datos individuales que componen la muestra.
   */
  results: SampleResult[];
}

export interface SampleState extends SimpleModel {
  code: SampleStateCode;
  name: string;
}

export enum SampleStateCode {
  E01 = 'E01',
  // etc.
}

/**
 * Agrupación de resultados relacionados con la muestra.
 */
export interface SampleResult extends SimpleModel {
  sampleId: number | undefined;
  data: SampleResultData[];
}

/**
 * Posibles tipos de datos que el valor de la muestra puede tomar.
 */
export type SampleDataValue =
  string
  | number
  | Partial<FieldModuleTerm>
  | null
  | undefined
  | Array<string | number | Partial<FieldModuleTerm> | null | undefined>;

/**
 * Definición base de la estructura de un dato de la muestra
 */
export interface BaseSampleData extends SimpleModel, TranslatedModel {
  /**
   * Identificador del campo-módulo relacionado
   */
  fieldId: number;
  /**
   * La propiedad almacena el texto, número o código final en formato string.
   * Valor final del dato.
   */
  value: string;
}

/**
 * Unidad de dato individual encontrado en los datos comunes de la muestra.
 */
export interface SampleData extends BaseSampleData {
  sampleId: number | undefined;
}

/**
 * Unidad de dato individual encontrado en los resultados de la muestra.
 */
export interface SampleResultData extends BaseSampleData {
  resultId: number | undefined;
}

// esto debería cambiarse por flags
export const STATIC_SAMPLE_STATES = {
  AESAN_EXPORTED: 7,
};

export interface SampleFilterForm extends AppQuerySource {
  pubCode?: string;
  sampleStateId?: number;
  seasonName?: string;
  createdAtGTE?: string;
  createdAtLTE?: string;
  updatedAtGTE?: string;
  updatedAtLTE?: string;
  state?: number[];
}
