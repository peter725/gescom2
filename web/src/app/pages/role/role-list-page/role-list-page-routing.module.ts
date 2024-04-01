import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleListPageComponent } from './role-list-page.component';


const routes: Routes = [
  {
    path: '',
    component: RoleListPageComponent,
    title: 'pages.role.list',
    data: { breadcrumb: 'generic.actions.list' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoleListPageRoutingModule {
}
