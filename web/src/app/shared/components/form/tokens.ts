import { InjectionToken } from '@angular/core';
import { ComponentStatus } from '@libs/commons';


export const FORM_STATUS = new InjectionToken<ComponentStatus<unknown>>(
  'Provides a component status instance to forms'
);
