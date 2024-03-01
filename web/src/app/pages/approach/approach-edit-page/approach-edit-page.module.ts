import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog';
import { TableModule } from '@base/shared/components/table';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { ApproachEditPageRoutingModule } from './approach-edit-page-routing.module';
import { ApproachEditPageComponent } from './approach-edit-page.component';
import {StateToggleModule} from "@base/shared/components/state-toggle";
import {MatChipsModule} from "@angular/material/chips";
import { MatRadioModule } from '@angular/material/radio';
import { TabPanelModule } from '@base/shared/components/tabpanel';


@NgModule({
    imports: [
        CommonModule,
        ApproachEditPageRoutingModule,
        CommonsModule,
        EditPageModule,
        TableModule,
        MatDialogModule,
        StateToggleModule,
        MatChipsModule,
        MatRadioModule,
        TabPanelModule
    ],
    declarations: [
        ApproachEditPageComponent,
    ],
})
export class ApproachEditPageModule {
}
