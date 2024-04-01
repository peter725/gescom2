import { RouterModule, Routes } from '@angular/router';
import { CampaignListPageComponent } from '@base/pages/campaign/campaign-list-page/campaign-list-page.component';
import { NgModule } from '@angular/core';

const routes: Routes = [{
  path: '',
  component: CampaignListPageComponent,
  title: 'pages.campaign.list',
  data: { breadcrumb: 'generic.actions.list' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CampaignListPageRoutingModule {
}