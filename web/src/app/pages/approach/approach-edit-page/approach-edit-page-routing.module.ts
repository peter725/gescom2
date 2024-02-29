import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApproachEditPageComponent } from './approach-edit-page.component';

const routes: Routes = [
    {
        path: '',
        component: ApproachEditPageComponent,
        title: 'pages.approach.edit',
        data: { breadcrumb: 'generic.actions.edit' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class ApproachEditPageRoutingModule {
}
