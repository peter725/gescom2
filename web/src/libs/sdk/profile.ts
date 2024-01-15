import { SimpleModel, StatefulModel, TranslatedModel } from './common';
import { Permission } from '@libs/sdk/permission';

export interface Profile extends SimpleModel, TranslatedModel,StatefulModel {
  name: string;
  permissions: Permission[];
}

export interface ProfileForm {
  id: number | null;
  name: string | null;
  permissions: Permission[] | null;
}
