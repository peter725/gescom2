import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileEditPageComponent } from './profile-edit-page.component';

const routes: Routes = [
  {
    path: '',
    component: ProfileEditPageComponent,
    title: 'pages.profile.edit',
    data: { breadcrumb: 'generic.actions.edit' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileEditPageRoutingModule {
}
