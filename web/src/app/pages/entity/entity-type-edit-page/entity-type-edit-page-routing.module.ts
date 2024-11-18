import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntityTypeEditPageComponent } from './entity-type-edit-page.component';

const routes: Routes = [
    {
        path: '',
        component: EntityTypeEditPageComponent,
        title: 'pages.entityType.edit',
        data: { breadcrumb: 'generic.actions.edit' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EntityTypeEditPageRoutingModule {
}
