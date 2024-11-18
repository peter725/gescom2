import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProtocolEditPageComponent } from './protocol-edit-page.component';

const routes: Routes = [{
  path: '',
  component: ProtocolEditPageComponent,
  title: 'pages.protocol.edit',
  data: { breadcrumb: 'generic.actions.edit' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProtocolEditPageRoutingModule {
}
