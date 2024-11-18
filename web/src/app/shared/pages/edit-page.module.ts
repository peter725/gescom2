import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TswSelectModule } from '@base/shared/select';
import { FormExtensionModule } from '@base/shared/components/form';
import { SectionModule } from '../components/section';

const COMMON_MODULES = [
  FormExtensionModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatTooltipModule,
  ReactiveFormsModule,
  SectionModule,
  TswSelectModule,
];

@NgModule({
  imports: COMMON_MODULES,
  exports: COMMON_MODULES,
})
export class EditPageModule {
}
