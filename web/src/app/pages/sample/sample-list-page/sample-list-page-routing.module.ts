import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SampleListPageComponent } from './sample-list-page.component';

const routes: Routes = [
  {
    path: '',
  component: SampleListPageComponent,
  title: 'pages.sample.list',
  data: { breadcrumb: 'generic.actions.list' },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SampleListPageRoutingModule { }
