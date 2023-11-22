import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{
    path: '',
    component: ApproachSeePageComponent,
    title: 'pages.user.list',
    data: { breadcrumb: 'generic.actions.see' },
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ApproachSeePageRoutingModule {
}
