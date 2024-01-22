import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {
  InfringementListPageComponent
} from '@base/pages/campaign/campaign-see-page/components/infringement/infringement-list-page/infringement-list-page.component';

const routes: Routes = [{
  path: '',
  component: InfringementListPageComponent,
  title: 'pages.infraction.list',
  data: { breadcrumb: 'generic.actions.list' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InfringementListPageRoutingModule {
}