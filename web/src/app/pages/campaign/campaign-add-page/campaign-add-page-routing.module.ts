import {
    CampaignAddPageComponent
} from "@base/pages/campaign/campaign-add-page/campaign-add-page.component";
import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";

const routes: Routes = [
    {
        path: '',
        component: CampaignAddPageComponent,
        title: 'pages.campaign.add',
        data: {breadcrumb: 'generic.actions.add'}
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CampaignAddPageRountingModule {
}