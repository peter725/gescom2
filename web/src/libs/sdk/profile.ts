import { SimpleModel, StatefulModel} from './common';
import { Permission } from './permission';

export interface Profile extends SimpleModel, StatefulModel {
  name: string;
  permissions: Permission[];
}

export interface ProfileForm {
  id: number | null;
  name: string | null;
  permissions: Permission[] | null;
}
