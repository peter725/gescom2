import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArbitrationStep3Component } from './arbitration-step3.component';

const routes: Routes = [
  {
    path: '',
    component: ArbitrationStep3Component,
    title: 'pages.arbitration.add',
    data: { breadcrumb: 'generic.actions.add' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArbitrationStep3RoutingModule {
}
