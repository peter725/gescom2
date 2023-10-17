import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {CommonsModule} from '@base/shared/pages/commons.module';
import {EditPageModule} from '@base/shared/pages/edit-page.module';
import {ArbitrationStep1RoutingModule} from './arbitration-step1-routing.module';
import {ArbitrationStep1Component} from './arbitration-step1.component';
import {MatRadioModule} from "@angular/material/radio";


@NgModule({
    imports: [
        CommonModule,
        ArbitrationStep1RoutingModule,
        CommonsModule,
        EditPageModule,
        MatRadioModule,
    ],
    declarations: [
        ArbitrationStep1Component
    ],
})
export class ArbitrationStep1Module {
}
