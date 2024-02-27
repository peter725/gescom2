import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IprComponent } from './ipr.component';

const routes: Routes = [{
  path: '',
  component: IprComponent,
  title: 'pages.user.add',
  data: { },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ResultadosRoutingModule {
}
