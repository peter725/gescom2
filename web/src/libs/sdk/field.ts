import { SimpleModel, StatefulModel, TranslatedModel } from './common';
import { Origin } from '@libs/sdk/origin';

export interface Field extends SimpleModel, StatefulModel, TranslatedModel {
  section: string;
  sectionCode: string;

  elementCode: string;
  elementName: string;
  elementLabel: string;
  elementNameWeb: string;
  elementMaxLength: string;
  elementTerminology: string | null;
  description: string;
  version: string;

  origin: Origin;
  elementFormat: Record<string, any>;
  fieldType: Record<string, any>;
}

export interface FieldForm {
  id: number | null;
  languageId: number | null;

  description: string | null;
  elementCode: string | null;
  elementFormat: Record<string, any> | null;
  elementLabel: string | null;
  elementMaxLength: number | null;
  elementName: string | null;
  elementNameWeb: string | null;
  elementTerminology: string | null;

  section: string | null;
  sectionCode: string | null;
  version: string;

  fieldType: Record<string, any> | null;
  origin: Record<string, any> | null;
}
