import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ProtocolQuestionListPageComponent } from './protocolQuestion-list-page.component';

const routes: Routes = [{
  path: '',
  component: ProtocolQuestionListPageComponent,
  title: 'pages.infringement.list',
  data: { breadcrumb: 'generic.actions.list' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class protocolQuestionListPageRoutingModule {
}