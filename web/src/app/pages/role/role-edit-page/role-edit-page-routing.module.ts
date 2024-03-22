import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleEditPageComponent } from './role-edit-page.component';

const routes: Routes = [
  {
    path: '',
    component: RoleEditPageComponent,
    title: 'pages.role.edit',
    data: { breadcrumb: 'generic.actions.edit' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoleEditPageRoutingModule {
}
