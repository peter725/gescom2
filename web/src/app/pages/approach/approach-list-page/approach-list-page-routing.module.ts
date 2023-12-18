import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApproachListPageComponent } from './approach-list-page.component';


const routes: Routes = [
  {
    path: '',
    component: ApproachListPageComponent,
    title: 'pages.arbitration.list',
    data: { breadcrumb: 'generic.actions.list' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ApproachListPageRoutingModule {
}
