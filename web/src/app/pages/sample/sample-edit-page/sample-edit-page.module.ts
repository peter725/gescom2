import { ScrollingModule } from '@angular/cdk/scrolling';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatStepperModule } from '@angular/material/stepper';
import { CatalogueBrowserModule } from '@base/shared/catalogue-browser';
import { TableModule } from '@base/shared/components/table';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { SampleDatasetModule } from '@base/shared/sample-dataset';
import { AuthorizationModule } from '@libs/security';
import { SampleEditPageRoutingModule } from './sample-edit-page-routing.module';
import { SampleEditPageComponent } from './sample-edit-page.component';
import { SampleEditPageConfigFormComponent } from './components';


@NgModule({
  imports: [
    CommonModule,
    SampleEditPageRoutingModule,
    AuthorizationModule,
    CatalogueBrowserModule,
    MatBadgeModule,
    MatButtonToggleModule,
    MatCheckboxModule,
    MatDialogModule,
    MatExpansionModule,
    MatListModule,
    MatProgressSpinnerModule,
    MatSlideToggleModule,
    MatStepperModule,
    MatRadioModule,
    SampleDatasetModule,
    SampleEditPageRoutingModule,
    ScrollingModule,
    TableModule,
    CommonsModule,
    EditPageModule,
  ],
  declarations: [
    SampleEditPageComponent,
    SampleEditPageConfigFormComponent,
  ]
})
export class SampleEditPageModule {
}
