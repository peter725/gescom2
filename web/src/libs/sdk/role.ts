import { SimpleModel, StatefulModel} from './common';
import { Permission } from './permission';

export interface Role extends SimpleModel, StatefulModel {
    role: string;
    permissions: Permission[];
}

export interface RoleForm {
    id: number | null;
    role: string | null;
    permissions: Permission[] | null;
}