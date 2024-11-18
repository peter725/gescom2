import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampaignEditPageComponent } from '@base/pages/campaign/campaign-edit-page/campaign-edit-page.component';

const routes: Routes = [
    {
      path: '',
      component: CampaignEditPageComponent,
      title: 'pages.campaign.edit',
      data: { breadcrumb: 'generic.actions.edit' },
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CampaignEditPageRoutingModule {
}

