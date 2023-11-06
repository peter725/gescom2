import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { ListPageModule } from '@base/shared/pages/list';
import { NamedRoutesModule } from '@libs/named-routes';
import { ProfileListPageRoutingModule } from './profile-list-page-routing.module';
import { ProfileListPageComponent } from './profile-list-page.component';
import {StateToggleModule} from "@base/shared/components/state-toggle";


@NgModule({
  imports: [
    ProfileListPageRoutingModule,
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
    ProfileListPageComponent
  ],
})
export class ProfileListPageModule {
}
