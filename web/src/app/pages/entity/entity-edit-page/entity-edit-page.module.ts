import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EntityEditPageRoutingModule } from './entity-edit-page-routing.module';
import {CommonsModule} from "@base/shared/pages/commons.module";
import {EditPageModule} from "@base/shared/pages/edit-page.module";
import {EntityEditPageComponent} from "@base/pages/entity/entity-edit-page/entity-edit-page.component";

@NgModule({
    imports: [
        CommonModule,
        EntityEditPageRoutingModule,
        CommonsModule,
        EditPageModule,
    ],
    declarations: [
        EntityEditPageComponent
    ],
})
export class EntityEditPageModule {
}
