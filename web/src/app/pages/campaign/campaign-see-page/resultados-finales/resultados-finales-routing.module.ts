import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResultadosFinalesComponent } from './resultados-finales.component';

const routes: Routes = [{
  path: '',
  component: ResultadosFinalesComponent,
  title: 'pages.campaign.finalResults',
  data: {breadcrumb: 'pages.results.title'}
},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ResultadosFinalesRoutingModule {
}
