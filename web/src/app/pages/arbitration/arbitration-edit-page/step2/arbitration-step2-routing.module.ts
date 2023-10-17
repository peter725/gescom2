import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArbitrationStep2Component } from './arbitration-step2.component';

const routes: Routes = [
  {
    path: '',
    component: ArbitrationStep2Component,
    title: 'pages.arbitration.add',
    data: { breadcrumb: 'generic.actions.add' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArbitrationStep2RoutingModule {
}
