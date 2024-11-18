import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntityEditPageComponent } from './entity-edit-page.component';


const routes: Routes = [
    {
        path: '',
        component: EntityEditPageComponent,
        title: 'pages.entity.add',
        data: { breadcrumb: 'generic.actions.add' },
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EntityEditPageRoutingModule {
}
