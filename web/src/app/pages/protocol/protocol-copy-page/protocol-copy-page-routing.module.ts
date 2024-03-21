import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProtocolCopyPageComponent } from './protocol-copy-page.component';

const routes: Routes = [{
  path: '',
  component: ProtocolCopyPageComponent,
  title: 'pages.protocol.copy',
  data: { breadcrumb: 'generic.actions.copy' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProtocolCopyPageRoutingModule {
}
