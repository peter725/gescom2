import { NgModule } from '@angular/core';
import { NamedRoutesModule, NamedRouteSource } from '@libs/named-routes';

const routes: NamedRouteSource[] = [
  { key: 'phone', value: '/app/inicio' },
  { key: 'login', value: '/auth/login' },
  { key: 'logout', value: '/auth/logout' },

  { key: 'userManagementCreate', value: '/app/usuarios/0' },
  { key: 'userManagementList', value: '/app/usuarios/consulta' },
  { key: 'userManagementEdit', value: '/app/usuarios/:id' },

];


@NgModule({
  imports: [NamedRoutesModule.forRoot(routes)],
  exports: [NamedRoutesModule],
})
export class NamedRoutesConfig {
}
