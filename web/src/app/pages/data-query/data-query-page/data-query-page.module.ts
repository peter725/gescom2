import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DataQueryPageComponent } from "./data-query-page.component";
import { DataQueryPageRoutingModule } from "./data-query-page-routing.module";
import { ListPageModule } from "@base/shared/pages/list-page.module";
import { CommonsModule } from "@base/shared/pages/commons.module";
import { MatFormFieldModule } from "@angular/material/form-field";
import { FormsModule } from "@angular/forms";
import { EditPageModule } from "@base/shared/pages/edit-page.module";
import { MatRadioModule } from '@angular/material/radio';
import { ScrollingModule } from "@angular/cdk/scrolling";
import { MatBadgeModule } from "@angular/material/badge";
import { MatButtonToggleModule } from "@angular/material/button-toggle";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatDialogModule } from "@angular/material/dialog";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatListModule } from "@angular/material/list";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { MatStepperModule } from "@angular/material/stepper";
import { TableModule } from "@base/shared/components/table";
import { AuthorizationModule } from "@libs/security";
import { DataQueryConfigComponent, DataQueryConfigFieldsComponent, DataQueryConfigPreparationComponent, DataQueryConfigSummaryComponent, DataQueryViewComponent, DataQueryViewFilterComponent } from "./components";
import { MatCardModule } from "@angular/material/card";

@NgModule({
    declarations: [
        DataQueryPageComponent,
        DataQueryConfigComponent,
        DataQueryConfigFieldsComponent,
        DataQueryConfigPreparationComponent,
        DataQueryConfigSummaryComponent,
        DataQueryViewComponent,
        DataQueryViewFilterComponent,
    ],
    imports: [
        DataQueryPageRoutingModule,
        CommonModule,
        ListPageModule,
        CommonsModule,
        MatFormFieldModule,
        FormsModule,
        EditPageModule,
        MatRadioModule,
        MatCheckboxModule,
        MatDialogModule,
        MatExpansionModule,
        MatProgressSpinnerModule,
        MatSlideToggleModule,
        MatStepperModule,
        TableModule,
        MatListModule,
        MatButtonToggleModule,
        MatBadgeModule,
        AuthorizationModule,
        ScrollingModule,
        MatCardModule
    ],

})
export class DataQueryPageModule {}