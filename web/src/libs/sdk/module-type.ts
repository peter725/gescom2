import { SimpleModel, TranslatedModel, StatefulModel } from './common';


export interface ModuleType extends SimpleModel, TranslatedModel, StatefulModel {
  name: string;
  description: string | null;
}

export interface ModuleTypeForm {
  id: number | null;
  languageId: number | string | null;
  name: string | null;
  description: string | null;
}
