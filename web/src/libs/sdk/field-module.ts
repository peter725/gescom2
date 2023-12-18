import { BaseModel, SimpleModel, StatefulAltModel, StatefulModel, TranslatedModel } from './common';
import { Field } from './field';
import { Module } from './module';
import { PositionField } from './position-field';
import { Required } from './requiered';
import { Visibility } from './visibility';
import { VisibilityWeb } from './visibility-web';

export interface FieldModule extends SimpleModel, StatefulModel, TranslatedModel {
  module: Module | null;
  field: Field | null;
  position: PositionField | null;
  visibility: Visibility | null;
  visibilityWeb: VisibilityWeb | null;
  required: Required | null;
  order: number | null;
  value: string | null;
  positionRow: string | null;
  positionColumn: string | null;

  hierarchyId: number | null;
  /*  keyValue: number | null;
   multilanguage: number | null;
   multiple: number | null;
   separator: string | null;
   exportableXml: number | null; */
}

export interface VFieldModule extends SimpleModel, StatefulAltModel {
  id: number;
  idField: number;
  elementCode: string;
  elementFormatId: number;
  elementFormatName: string;
  elementLabel: string;
  elementName: string;
  elementDescription: string;
  elementOrder: number;
  elementStateId: number;
  elementStateName: string;
  elementType: FieldElementType;
  elementVersion: string;
  exportableXml: boolean;
  extElementTerminology: string | null;
  extCatalogueId: number | null;
  extCatalogueCode: string | null;
  extHierarchyId: number | null;
  extHierarchyCode: string | null;
  fmcKeyValue: boolean;
  hidden: boolean;
  languageCode: string;
  languageId: number;
  moduleId: number;
  moduleName: string;
  moduleStateId: number;
  moduleStateName: string;
  multiLanguage: boolean;
  multiple: boolean;
  originId: number;
  originName: string;
  idPosition: number;
  positionName: string;
  positionType: string;
  positionRow: number;
  positionColumn: number;
  positionIsRepeatable: boolean;
  required: boolean;
  requiredId: number;
  requiredName: string;
  scopeVisibilityId: number;
  scopeVisibilityName: string;
  sectionCode: string;
  sectionName: string;
  sectionOrder: number;
  storedValue: number;
  // Valor por defecto para el campo. En algunos casos debemos interpretar
  // algún valor dinámico para el campo.
  valueDefault: string | null;
  valueMaxLength: string;
  valueSeparator: string | null;
  webVisibilityId: number;
  webVisibilityName: string;
}

export enum FieldElementType {
  CAT = 'CAT',
  COMBO = 'COMBO',
  NUMERIC = 'NUMERIC',
  TEXT = 'TEXT',
}

/**
 * Códigos de posiciones/secciones predefinidas a tener en cuenta
 */
export const FieldPositionType = {
  MUESTRA: 'MUESTRA',
  PROG: 'PROG',
  LABORATORIO: 'LABORATORIO',
  RESULTADOS: 'RESULTADOS',
};

/**
 * Códigos de campos que necesitamos tener en cuenta manualmente.
 */
export const StaticFieldNames = {
  SAMPLE_ID: 'sampId',
  RESULT_ID: 'resId',
  EVAL_CODE: 'evalCode',
  PARAM_NAME: 'paramName',
  PARAM_TYPE: 'paramType',
};

export const DefaultValuesCodes = {
  YEAR: 'YEAR', // Sustituir por el año actual
  PREV_YEAR: 'YEAR-1', // Sustituir por el año anterior
  SEQUENCE: 'SEQUENCE', // Secuencia automática
};

export interface FieldModuleForm {
  id: number | null;
  module: Module | null;
  field: Field | null;
  position: PositionField | null;
  visibility: Visibility | null;
  visibilityWeb: VisibilityWeb | null;
  required: Required | null;
  value: string | null;
  positionRow: number | null;
  positionColumn: number | null;

  /*  keyValue: number | null;
   multilanguage: number | null;
   multiple: number | null;
   separator: string | null;
   exportableXml: number | null; */
}

export interface FieldModuleUpdate {
  id: number | null;
  module: Module;
  field: BaseModel;
  visibility: Partial<Visibility>;
  visibilityWeb: Partial<VisibilityWeb>;
  required: Partial<Required> | null;

  position: Partial<PositionField>;
  positionRow: number | null;
  positionColumn: number | null;

  value: string | null;
  separator: string | null;

  multiLanguage: boolean;
  multiple: boolean;
  exportableXml: boolean;
  keyValue: number;

  hierarchyId: number | null;
}
