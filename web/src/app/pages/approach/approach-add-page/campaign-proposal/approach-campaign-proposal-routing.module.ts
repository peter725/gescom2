import {
    ApproachCampaignProposalComponent
} from "@base/pages/approach/approach-add-page/campaign-proposal/approach-campaign-proposal.component";
import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";

const routes: Routes = [
    {
        path: '',
        component: ApproachCampaignProposalComponent,
        title: 'pages.approach.add',
        data: {breadcrumb: 'generic.actions.add'}
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ApproachCampaignProposalRoutingModule {
}