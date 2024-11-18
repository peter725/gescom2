import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EntityTypeEditPageRoutingModule } from './entity-type-edit-page-routing.module';
import { EntityTypeEditPageComponent } from './entity-type-edit-page.component';
import {CommonsModule} from "@base/shared/pages/commons.module";
import {EditPageModule} from "@base/shared/pages/edit-page.module";

@NgModule({
    imports: [
        CommonModule,
        EntityTypeEditPageRoutingModule,
        CommonsModule,
        EditPageModule,
    ],
    declarations: [
        EntityTypeEditPageComponent
    ],
})
export class EntityTypeEditPageModule {
}
