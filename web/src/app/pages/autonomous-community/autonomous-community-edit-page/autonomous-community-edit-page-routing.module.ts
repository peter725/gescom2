import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutonomousCommunityEditPageComponent } from './autonomous-community-edit-page.component';

const routes: Routes = [
    {
        path: '',
        component: AutonomousCommunityEditPageComponent,
        title: 'pages.ccaa.edit',
        data: { breadcrumb: 'generic.actions.edit' }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AutonomousCommunityEditPageRoutingModule {
}
