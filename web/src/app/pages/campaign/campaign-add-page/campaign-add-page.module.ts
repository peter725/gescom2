import {NgModule} from "@angular/core";
import {
    CampaignAddPageComponent
} from "@base/pages/campaign/campaign-add-page/campaign-add-page.component";
import {CommonModule} from "@angular/common";
import {CommonsModule} from "@base/shared/pages/commons.module";
import {EditPageModule} from "@base/shared/pages/edit-page.module";
import {MatRadioModule} from "@angular/material/radio";
import {TabPanelModule} from "@base/shared/components/tabpanel";
import {
    CampaignAddPageRountingModule
} from "@base/pages/campaign/campaign-add-page/campaign-add-page-routing.module";

import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
imports: [
    CommonModule,
    CampaignAddPageRountingModule,
    CommonsModule,
    EditPageModule,
    MatRadioModule,
    TabPanelModule,
    ReactiveFormsModule
],
    declarations: [
        CampaignAddPageComponent],
})
export class CampaignAddPageModule {
}