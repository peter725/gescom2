import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ListPageModule } from '@base/shared/pages/list';
import { NamedRoutesModule } from '@libs/named-routes';
import { ModuleListPageRoutingModule } from './module-list-page-routing.module';
import { ModuleListPageComponent } from './module-list-page.component';
import {CommonsModule} from "@base/shared/pages/commons.module";
import {StateToggleModule} from "@base/shared/components/state-toggle";

@NgModule({
    imports: [
        ModuleListPageRoutingModule,
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
        ModuleListPageComponent
    ],
})
export class ModuleListPageModule {
}
