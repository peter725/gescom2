import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModuleEditPageComponent } from './module-edit-page.component';

const routes: Routes = [
    {
        path: '',
        component: ModuleEditPageComponent,
        title: 'pages.module.edit',
        data: { breadcrumb: 'generic.actions.edit' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ModuleEditPageRoutingModule {
}
