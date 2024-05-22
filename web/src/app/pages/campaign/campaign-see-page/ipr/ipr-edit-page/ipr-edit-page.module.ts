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
import { IprEditPageComponent } from './ipr-edit-page.component';
import { ResultadosRoutingModule } from './ipr-edit-page-routing.module';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { MatChipsModule } from '@angular/material/chips';
import { InfringementDialogComponent } from '@base/pages/infringement-dialog/infringement-dialog.component';

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
    CommonModule,
    CommonsModule,
    EditPageModule,
    MatChipsModule,
    InfringementDialogComponent
  ],
  declarations: [
    IprEditPageComponent
  ],
  exports: [
    IprEditPageComponent
  ]
})
export class IprEditPageModule {

}