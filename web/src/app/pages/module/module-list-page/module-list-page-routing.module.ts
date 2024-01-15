import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModuleListPageComponent } from './module-list-page.component';

const routes: Routes = [
    {
        path: '',
        component: ModuleListPageComponent,
        title: 'pages.module.list',
        data: { breadcrumb: 'generic.actions.list' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ModuleListPageRoutingModule {
}
