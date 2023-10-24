import { InjectionToken } from '@angular/core';
import { ACL, AuthorizationConfig } from './models';


export const ACL_TOKEN = new InjectionToken<Promise<ACL>>(
  'Authorization resource definition'
);

export const AUTHORIZATION_OPTIONS = new InjectionToken<AuthorizationConfig>(
  'Authentication module configuration'
);
