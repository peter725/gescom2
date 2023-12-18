import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListPageComponent } from './user-list-page.component';

const routes: Routes = [{
  path: '',
  component: UserListPageComponent,
  title: 'pages.user.list',
  data: { breadcrumb: 'generic.actions.list' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserListPageRoutingModule {
}
