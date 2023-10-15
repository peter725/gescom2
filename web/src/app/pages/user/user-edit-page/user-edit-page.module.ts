import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog';
import { TableModule } from '@base/shared/components/table';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { UserEditScopeFormComponent, UserEditScopeListComponent } from './components';
import { UserEditPageRoutingModule } from './user-edit-page-routing.module';
import { UserEditPageComponent } from './user-edit-page.component';
import {StateToggleModule} from "@base/shared/components/state-toggle";


@NgModule({
  imports: [
    CommonModule,
    UserEditPageRoutingModule,
    CommonsModule,
    EditPageModule,
    TableModule,
    MatDialogModule,
    StateToggleModule,
  ],
  declarations: [
    UserEditPageComponent,
    UserEditScopeFormComponent,
    UserEditScopeListComponent
  ],
})
export class UserEditPageModule {
}
