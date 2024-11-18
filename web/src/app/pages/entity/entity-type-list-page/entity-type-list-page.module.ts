import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EntityTypeListPageRoutingModule } from './entity-type-list-page-routing.module';
import { EntityTypeListPageComponent } from './entity-type-list-page.component';
import {CommonsModule} from "@base/shared/pages/commons.module";
import {ListPageModule} from "@base/shared/pages/list-page.module";
import {StateToggleModule} from "@base/shared/components/state-toggle";

@NgModule({
    imports: [
        CommonModule,
        EntityTypeListPageRoutingModule,
        CommonsModule,
        ListPageModule,
        StateToggleModule,
    ],
    declarations: [
        EntityTypeListPageComponent
    ],
})
export class EntityTypeListPageModule {
}
