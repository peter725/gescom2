import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModuleTypeEditPageComponent } from './module-type-edit-page.component';

const routes: Routes = [
    {
        path: '',
        component: ModuleTypeEditPageComponent,
        title: 'pages.moduleType.edit',
        data: { breadcrumb: 'generic.actions.edit' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ModuleTypeEditPageRoutingModule {
}
