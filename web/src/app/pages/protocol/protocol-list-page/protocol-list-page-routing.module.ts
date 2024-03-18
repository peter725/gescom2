import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProtocolListPageComponent } from './protocol-list-page.component';


const routes: Routes = [
  {
    path: '',
    component: ProtocolListPageComponent,
    title: 'pages.protocol.list',
    data: { breadcrumb: 'generic.actions.list' }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProtocolListPageRoutingModule {
}
