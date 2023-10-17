import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { ListPageModule } from '@base/shared/pages/list';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { UserListPageRoutingModule } from './user-list-page-routing.module';
import { UserListPageComponent } from './user-list-page.component';
import {StateToggleModule} from "@base/shared/components/state-toggle";
import {MatCardModule} from "@angular/material/card";

@NgModule({
  imports: [
    UserListPageRoutingModule,
    CommonModule,
    CommonsModule,
    ListPageModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSlideToggleModule,
    StateToggleModule,
    MatCardModule,
  ],
  declarations: [
    UserListPageComponent,
  ],
})
export class UserListPageModule {
}
