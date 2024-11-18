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
import { ResultadosComponent } from './resultados.component';
import { ResultadosRoutingModule } from './resultados-routing.module';
import { MatStepperModule } from '@angular/material/stepper';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { ResultadosSeeComponent } from './resultados-see.component';
import { FormExtensionModule } from '@base/shared/components/form';
import { ConfirmationModule } from '@base/shared/confirmation';

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
    ResultadosRoutingModule,
    MatStepperModule,
    MatSelectModule,
    MatCardModule,
    FormExtensionModule,
    ConfirmationModule
  ],
  declarations: [
    ResultadosComponent,
    ResultadosSeeComponent
  ],
  exports: [
    ResultadosComponent,
    ResultadosSeeComponent
  ]
})
export class ResultadosModule {

}