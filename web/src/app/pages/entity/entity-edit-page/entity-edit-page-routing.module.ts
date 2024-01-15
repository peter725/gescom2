import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntityEditPageComponent } from './entity-edit-page.component';


const routes: Routes = [
    {
        path: '',
        component: EntityEditPageComponent,
        title: 'pages.entity.edit',
        data: { breadcrumb: 'generic.actions.edit' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EntityEditPageRoutingModule {
}
