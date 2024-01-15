import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {ListPageModule} from '@base/shared/pages/list';
import { NamedRoutesModule } from '@libs/named-routes';
import { ModuleTypeListPageComponent } from './module-type-list-page.component';
import {CommonsModule} from "@base/shared/pages/commons.module";
import {ModuleTypeListPageRoutingModule} from "@base/pages/module/module-type-list-page/module-type-list-page-routing.module";
import {StateToggleModule} from "@base/shared/components/state-toggle";

@NgModule({
    imports: [
        CommonModule,
        ModuleTypeListPageRoutingModule,
        CommonsModule,
        ListPageModule,
        FormsModule,
        MatFormFieldModule,
        MatInputModule,
        NamedRoutesModule,
        StateToggleModule,
    ],
  declarations: [
    ModuleTypeListPageComponent
  ],
})
export class ModuleTypeListPageModule {
}
