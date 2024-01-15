import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import { CampaignSeePageComponent } from '@base/pages/campaign/campaign-see-page/campaign-see-page.component';

const routes: Routes = [
    {
        path: '',
        component: CampaignSeePageComponent,
        title: 'pages.campaign.see',
        data: {breadcrumb: 'generic.actions.see'}
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CampaignSeePageRoutingModule {
}