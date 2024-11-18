import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ModuleEditPageRoutingModule } from './module-edit-page-routing.module';
import { ModuleEditPageComponent } from './module-edit-page.component';
import {CommonsModule} from "@base/shared/pages/commons.module";
import {EditPageModule} from "@base/shared/pages/edit-page.module";

@NgModule({
    imports: [
        CommonModule,
        ModuleEditPageRoutingModule,
        CommonsModule,
        EditPageModule,
    ],
    declarations: [
        ModuleEditPageComponent
    ],
})
export class ModuleEditPageModule {
}
