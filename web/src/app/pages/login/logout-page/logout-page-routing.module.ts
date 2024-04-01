import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogoutPageComponent } from './logout-page.component';


const routes: Routes = [{
  path: '',
  component: LogoutPageComponent,
  title: 'pages.logout.title'
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LogoutPageRoutingModule {
}
