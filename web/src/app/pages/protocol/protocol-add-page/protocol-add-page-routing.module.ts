import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProtocolAddPageComponent } from './protocol-add-page.component';

const routes: Routes = [{
  path: '',
  component: ProtocolAddPageComponent,
  title: 'pages.user.add',
  data: { breadcrumb: 'generic.actions.add' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProtocolAddPageRoutingModule {
}
