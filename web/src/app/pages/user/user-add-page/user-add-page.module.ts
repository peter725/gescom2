import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { UserAddPageRoutingModule } from './user-add-page-routing.module';
import { UserAddPageComponent } from './user-add-page.component';
import {MatChipsModule} from "@angular/material/chips";


@NgModule({
    imports: [
        CommonModule,
        UserAddPageRoutingModule,
        CommonsModule,
        EditPageModule,
        MatChipsModule,
    ],
  declarations: [
    UserAddPageComponent
  ],
})
export class UserAddPageModule {
}
