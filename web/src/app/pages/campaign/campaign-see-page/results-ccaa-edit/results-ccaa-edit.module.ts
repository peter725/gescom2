import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListPageModule } from '@base/shared/pages/list-page.module';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { StateToggleModule } from '@base/shared/components/state-toggle';
import { BreadcrumbsModule } from '@base/shared/components/breadcrumbs';
import { TranslateModule } from '@ngx-translate/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { NamedRoutesModule } from '@libs/named-routes';
import { ResultsCcaaEditComponent } from './results-ccaa-edit.component';
import { ResultsCcaaEditRoutingModule } from './results-ccaa-edit-routing.module';
import { MatStepperModule } from '@angular/material/stepper';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';

@NgModule({
  imports: [
    CommonModule,
    ListPageModule,
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSlideToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    StateToggleModule,
    BreadcrumbsModule,
    TranslateModule,
    MatButtonModule,
    MatIconModule,
    NamedRoutesModule,
    ResultsCcaaEditRoutingModule,
    MatStepperModule,
    MatSelectModule,
    MatCardModule
  ],
  declarations: [
    ResultsCcaaEditComponent
  ],
  exports: [
    ResultsCcaaEditComponent
  ]
})
export class ResultadosCaaEditModule {

}