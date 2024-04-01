import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EntityListPageRoutingModule } from './entity-list-page-routing.module';
import { EntityListPageComponent } from './entity-list-page.component';
import {CommonsModule} from "@base/shared/pages/commons.module";
import {ListPageModule} from "@base/shared/pages/list-page.module";
import {StateToggleModule} from "@base/shared/components/state-toggle";

@NgModule({
    imports: [
        CommonModule,
        EntityListPageRoutingModule,
        CommonsModule,
        ListPageModule,
        StateToggleModule,
    ],
    declarations: [
        EntityListPageComponent
    ],
})
export class EntityListPageModule {
}
