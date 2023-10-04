import { NgModule } from '@angular/core';
import { NamedRoutesModule, NamedRouteSource } from '@libs/named-routes';

const routes: NamedRouteSource[] = [
  { key: 'home', value: '/app/inicio' },
  { key: 'login', value: '/auth/login' },
  { key: 'logout', value: '/auth/logout' },

  { key: 'arbitrationManagementCreate', value: '/app/arbitration/0' },

];


@NgModule({
  imports: [NamedRoutesModule.forRoot(routes)],
  exports: [NamedRoutesModule],
})
export class NamedRoutesConfig {
}
