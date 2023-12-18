import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileListPageComponent } from './profile-list-page.component';


const routes: Routes = [
  {
    path: '',
    component: ProfileListPageComponent,
    title: 'pages.profile.list',
    data: { breadcrumb: 'generic.actions.list' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileListPageRoutingModule {
}
