import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModuleTypeListPageComponent } from './module-type-list-page.component';


const routes: Routes = [
    {
        path: '',
        component: ModuleTypeListPageComponent,
        title: 'pages.moduleType.list',
        data: { breadcrumb: 'generic.actions.list' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ModuleTypeListPageRoutingModule {
}
