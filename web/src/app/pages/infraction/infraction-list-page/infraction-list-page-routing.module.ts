import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {
  InfractionListPageComponent
} from '@base/pages/infraction/infraction-list-page/infraction-list-page.component';

const routes: Routes = [{
  path: '',
  component: InfractionListPageComponent,
  title: 'pages.infraction.list',
  data: { breadcrumb: 'generic.actions.list' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InfractionListPageRoutingModule {
}