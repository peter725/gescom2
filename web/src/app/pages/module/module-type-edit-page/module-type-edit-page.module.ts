import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { ModuleTypeEditPageRoutingModule } from './module-type-edit-page-routing.module';
import { ModuleTypeEditPageComponent } from './module-type-edit-page.component';
import {CommonsModule} from "@base/shared/pages/commons.module";

@NgModule({

    imports: [
        CommonModule,
        ModuleTypeEditPageRoutingModule,
        CommonsModule,
        EditPageModule,
    ],
    declarations: [
        ModuleTypeEditPageComponent
    ],
})
export class ModuleTypeEditPageModule {
}
