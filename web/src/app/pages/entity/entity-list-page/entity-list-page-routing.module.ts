import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntityListPageComponent } from './entity-list-page.component';

const routes: Routes = [
    {
        path: '',
        component: EntityListPageComponent,
        title: 'pages.entity.list',
        data: { breadcrumb: 'generic.actions.list' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EntityListPageRoutingModule {
}
