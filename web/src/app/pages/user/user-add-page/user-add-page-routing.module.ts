import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserAddPageComponent } from './user-add-page.component';

const routes: Routes = [{
  path: '',
  component: UserAddPageComponent,
  title: 'pages.user.add',
  data: { breadcrumb: 'generic.actions.add' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserAddPageRoutingModule {
}
