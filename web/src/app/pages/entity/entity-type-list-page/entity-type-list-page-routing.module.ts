import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntityTypeListPageComponent } from './entity-type-list-page.component';

const routes: Routes = [
    {
        path: '',
        component: EntityTypeListPageComponent,
        title: 'pages.entityType.title',
        data: { breadcrumb: 'generic.actions.list' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EntityTypeListPageRoutingModule {
}
