import { InjectionToken } from '@angular/core';
import {  AuthorizationConfig } from './models';

export const AUTHORIZATION_OPTIONS = new InjectionToken<AuthorizationConfig>(
  'Authentication module configuration'
);
