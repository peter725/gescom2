import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { FormExtensionModule } from '@tulsa/app/shared/components/form';
import { SectionModule } from '@tulsa/app/shared/components/section';
import { TableModule } from '@tulsa/app/shared/components/table';
import { TulsaCommonsModule } from '@tulsa/app/shared/pages/tulsa-commons.module';
import { TswSelectModule } from '@tulsa/app/shared/select';
import {
  CatCodeBuilderDialogComponent,
  CatCodeGeneratorComponent,
  CatCodeViewerComponent,
  CatFilterComponent,
} from './components';


const EXPORTED_DECLARATIONS = [
  CatCodeBuilderDialogComponent,
  CatCodeGeneratorComponent,
  CatCodeViewerComponent,
  CatFilterComponent,
];

@NgModule({
  imports: [
    CommonModule,
    FormExtensionModule,
    MatCardModule,
    MatCheckboxModule,
    MatDialogModule,
    MatInputModule,
    ReactiveFormsModule,
    SectionModule,
    TableModule,
    TswSelectModule,
    TulsaCommonsModule,
    MatProgressSpinnerModule,
    MatSlideToggleModule,
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class CatalogueBrowserModule {
}
