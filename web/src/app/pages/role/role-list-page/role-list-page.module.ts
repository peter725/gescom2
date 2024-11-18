import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { ListPageModule } from '@base/shared/pages/list';
import { NamedRoutesModule } from '@libs/named-routes';
import {StateToggleModule} from "@base/shared/components/state-toggle";
import { RoleListPageComponent } from './role-list-page.component';
import { RoleListPageRoutingModule } from './role-list-page-routing.module';
import { RoleListPageFilterComponent } from '@base/pages/role/role-list-page/components';


@NgModule({
  imports: [
    RoleListPageRoutingModule,
    CommonModule,
    CommonsModule,
    ListPageModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    NamedRoutesModule,
    StateToggleModule,
  ],
  declarations: [
    RoleListPageComponent,
    RoleListPageFilterComponent,
  ],
})
export class RoleListPageModule {
}
