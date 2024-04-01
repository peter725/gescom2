import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResultadosComponent } from './resultados.component';
import { ResultadosSeeComponent } from './resultados-see.component';

const routes: Routes = [{
  path: '',
  component: ResultadosComponent,
  title: 'pages.campaign.finalResults',
  data: {breadcrumb: 'pages.results.title'}
},
{
  path: 'ver',
  component: ResultadosSeeComponent,
  title: 'pages.campaign.finalResults',
  data: {breadcrumb: 'pages.results.title'}
},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ResultadosRoutingModule {
}
