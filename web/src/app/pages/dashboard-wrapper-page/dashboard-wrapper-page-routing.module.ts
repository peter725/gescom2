import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { dashboardRoutes } from '../routes_app';
import { DashboardWrapperPageComponent } from './dashboard-wrapper-page.component';


const routes: Routes = [
  {
    path: '',
    component: DashboardWrapperPageComponent,
    children: dashboardRoutes,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardWrapperPageRoutingModule {
}
