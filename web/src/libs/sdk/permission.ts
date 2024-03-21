import { SimpleModel, TranslatedModel, StatefulModel } from './common';


export interface Permission extends SimpleModel, TranslatedModel, StatefulModel {
  name: string;
  description: string | null;
}

export interface PermissionForm {
  id: number | null;
  code: string | null;
  name: string | null;
}
