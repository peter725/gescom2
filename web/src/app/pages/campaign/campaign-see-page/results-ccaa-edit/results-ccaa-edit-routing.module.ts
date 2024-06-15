import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResultsCcaaEditComponent } from './results-ccaa-edit.component';

const routes: Routes = [{
  path: '',
  component: ResultsCcaaEditComponent,
  title: 'pages.campaign.finalResults',
  data: {breadcrumb: 'pages.results.title'}
},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ResultsCcaaEditRoutingModule {
}
