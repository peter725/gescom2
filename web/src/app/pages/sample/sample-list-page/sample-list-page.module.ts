import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { FormExtensionModule } from '@base/shared/components/form';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { ListPageModule } from '@base/shared/pages/list-page.module';
import { TswSelectModule } from '@base/shared/select';
import { FileUtilsModule } from '@libs/file';
import { NamedRoutesModule } from '@libs/named-routes';
import { SampleUploaderComponent } from './components';
import { SampleListPageFilterComponent } from './components/sample-list-page-filter/sample-list-page-filter.component';
import { SampleListPageRoutingModule } from './sample-list-page-routing.module';
import { SampleListPageComponent } from './sample-list-page.component';
import {StateToggleModule} from "@base/shared/components/state-toggle";


@NgModule({
  imports: [
    SampleListPageRoutingModule,
    CommonModule,
    CommonsModule,
    ListPageModule,
    ReactiveFormsModule,
    FormExtensionModule,
    FileUtilsModule,
    MatFormFieldModule,
    MatInputModule,
    NamedRoutesModule,
    TswSelectModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatDatepickerModule,
    StateToggleModule,
  ],
  declarations: [
    SampleListPageComponent,
    SampleUploaderComponent,
    SampleListPageFilterComponent,
  ],
})
export class SampleListPageModule {
}
