import { InjectionToken } from '@angular/core';
import { NamedRoutesStorage } from './models';


export const APP_ROUTES = new InjectionToken<NamedRoutesStorage>(
  'Application internal routes'
);
