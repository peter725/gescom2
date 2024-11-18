import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {
  InfringementListPageComponent
} from '@base/pages/infringement/infringement-list-page/infringement-list-page.component';

const routes: Routes = [{
  path: '',
  component: InfringementListPageComponent,
  title: 'pages.infringement.list',
  data: { breadcrumb: 'generic.actions.list' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InfringementListPageRoutingModule {
}