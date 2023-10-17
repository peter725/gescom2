import { SimpleModel, TranslatedModel, StatefulModel } from './common';


export interface EntityType extends SimpleModel, TranslatedModel, StatefulModel {
  name: string;
  description: string | null;
}

export interface EntityTypeForm {
  id: number | null;
  languageId: number | string | null;
  name: string | null;
  description: string | null;
}
