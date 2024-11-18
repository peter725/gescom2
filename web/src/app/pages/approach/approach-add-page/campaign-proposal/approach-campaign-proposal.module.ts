import {NgModule} from "@angular/core";
import {
    ApproachCampaignProposalComponent
} from "@base/pages/approach/approach-add-page/campaign-proposal/approach-campaign-proposal.component";
import {CommonModule} from "@angular/common";
import {CommonsModule} from "@base/shared/pages/commons.module";
import {EditPageModule} from "@base/shared/pages/edit-page.module";
import {MatRadioModule} from "@angular/material/radio";
import {TabPanelModule} from "@base/shared/components/tabpanel";
import {
    ApproachCampaignProposalRoutingModule
} from "@base/pages/approach/approach-add-page/campaign-proposal/approach-campaign-proposal-routing.module";

@NgModule({
imports: [
    CommonModule,
    ApproachCampaignProposalRoutingModule,
    CommonsModule,
    EditPageModule,
    MatRadioModule,
    TabPanelModule,
],
    declarations: [
    ApproachCampaignProposalComponent],
})
export class ApproachCampaignProposalModule {
}