import { InjectionToken } from '@angular/core';
import { CrudOperationStorage } from './operations';


export const CRUD_OPERATIONS = new InjectionToken<CrudOperationStorage>('Stores all available crud operation definitions');
