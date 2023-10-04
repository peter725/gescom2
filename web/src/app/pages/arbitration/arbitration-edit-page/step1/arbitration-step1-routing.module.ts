import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ArbitrationStep1Component} from './arbitration-step1.component';

const routes: Routes = [
    {
        path: '',
        component: ArbitrationStep1Component,
        title: 'pages.arbitration.add',
        data: {breadcrumb: 'generic.actions.add'}
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ArbitrationStep1RoutingModule {
}
