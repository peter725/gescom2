import { NgModule } from '@angular/core';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { FormMapper, SameTypeFormMapper } from '@libs/commons/form';

@NgModule({
  providers: [
    { provide: FormMapper, useClass: SameTypeFormMapper, },
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'outline' } }
  ]
})
export class FormConfig {}
