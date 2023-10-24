import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserEditPageComponent } from './user-edit-page.component';

const routes: Routes = [
    {
        path: '',
        component: UserEditPageComponent,
        title: 'pages.user.edit',
        data: { breadcrumb: 'generic.actions.edit' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class UserEditPageRoutingModule {
}
