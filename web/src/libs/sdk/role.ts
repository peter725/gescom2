import { SimpleModel, StatefulModel} from './common';
import { Permission } from './permission';
import { AppQuerySource } from '@libs/commons';

export interface Role extends SimpleModel, StatefulModel {
    role: string;
    permissions: Permission[];
}

export interface RoleForm {
    id: number | null;
    name: string | null;
    modules: RoleModule[] | null;
}

export interface RoleModule {
    module: string | null;
    permissions: Permission[] | null;
}

export interface RoleFilterForm extends AppQuerySource {
  name?: string;
  state?: number[];

}