import { SimpleModel, StatefulModel, TranslatedModel } from './common';
import { ModuleType } from './module-type';

export interface Module extends SimpleModel, TranslatedModel, StatefulModel  {
  name: string;
  description: string;
  code: string;
  type: ModuleType;
}

export interface ModuleForm {
  id: number | null;
  languageId: number | string | null;
  name: string | null;
  description: string | null;
  code: string | null;
  type: ModuleType | null;
}
