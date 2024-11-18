import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataQueryPageComponent } from './data-query-page.component';


const routes: Routes = [
    {
        path: '',
        component: DataQueryPageComponent,
        title: 'pages.dataQuery.list',
        data: { breadcrumb: 'generic.actions.list' }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DataQueryPageRoutingModule {
}