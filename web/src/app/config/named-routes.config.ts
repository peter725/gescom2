import { NgModule } from '@angular/core';
import { NamedRoutesModule, NamedRouteSource } from '@libs/named-routes';

const routes: NamedRouteSource[] = [
  { key: 'home', value: '/app/inicio' },
  { key: 'login', value: '/auth/login' },
  { key: 'logout', value: '/auth/logout' },

  { key: 'userManagementCreate', value: '/app/usuarios/0' },
  { key: 'userManagementList', value: '/app/usuarios/consulta' },
  { key: 'userManagementEdit', value: '/app/usuarios/:id' },

  { key: 'approachManagementCreate', value: '/app/propuestas/0' },

];


@NgModule({
  imports: [NamedRoutesModule.forRoot(routes)],
  exports: [NamedRoutesModule],
})
export class NamedRoutesConfig {
}
