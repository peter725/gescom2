import { BaseModel, TranslatedModel } from './common';


/**
 * Debemos revisar los datos porque no coinciden
 */
export interface FieldModuleTerm extends BaseModel {
  idFieldModule: number;
  idModule: number;
  idField: number;
  idLanguage: number;
  codeIso: string;
  elementCode: string;
  elementName: string;
  code: string;
  value: string;
  sortOrder: number;
}

export interface FieldModuleTermForm {
  fieldModuleId: number | null;
  catalogueId: number;
  hierarchyId: number;
  termId: number;
  typeId: number;
  forceSendText: boolean;
  sortOrder: number;
}

export interface VFieldModuleTerm extends BaseModel, TranslatedModel {
  fieldModuleId: number;
  moduleId: number;
  fieldId: number;
  elementCode: string;
  elementName: string;
  termId: number;
  code: string;
  value: string;
  sortOrder: number;
}
