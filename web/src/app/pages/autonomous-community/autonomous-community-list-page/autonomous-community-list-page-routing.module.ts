import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutonomousCommunityListPageComponent } from './autonomous-community-list-page.component';


const routes: Routes = [
    {
        path: '',
        component: AutonomousCommunityListPageComponent,
        title: 'pages.ccaa.list',
        data: { breadcrumb: 'generic.actions.list' }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AutonomousCommunityListPageRoutingModule {
}
