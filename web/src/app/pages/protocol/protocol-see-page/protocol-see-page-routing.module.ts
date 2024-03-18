import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProtocolSeePageComponent } from './protocol-see-page.component';

const routes: Routes = [{
  path: '',
  component: ProtocolSeePageComponent,
  title: 'pages.protocol.see',
  data: { breadcrumb: 'generic.actions.see' },
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProtocolSeePageRoutingModule {
}
