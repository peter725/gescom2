import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApproachSeePageComponent } from './approach-see-page.component';

const routes: Routes = [{
    path: '',
    component: ApproachSeePageComponent,
    title: 'pages.approach.see',
    data: { breadcrumb: 'generic.actions.see' },
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ApproachSeePageRoutingModule {
}
