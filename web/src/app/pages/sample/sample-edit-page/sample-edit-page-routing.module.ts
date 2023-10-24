import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SampleEditPageComponent } from './sample-edit-page.component';


const routes: Routes = [{
  path: '',
  component: SampleEditPageComponent,
  title: 'pages.sample.edit',
  data: { breadcrumb: 'generic.actions.edit' },
}];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SampleEditPageRoutingModule { }
