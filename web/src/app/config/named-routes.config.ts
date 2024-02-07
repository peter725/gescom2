import { NgModule } from '@angular/core';
import { NamedRoutesModule, NamedRouteSource } from '@libs/named-routes';

const routes: NamedRouteSource[] = [
  { key: 'home', value: '/app/inicio' },
  { key: 'login', value: '/auth/login' },
  { key: 'logout', value: '/auth/logout' },

  { key: 'userManagementCreate', value: '/app/usuarios/0' },
  { key: 'userManagementList', value: '/app/usuarios/consulta' },
  { key: 'userManagementEdit', value: '/app/usuarios/:id' },

  { key: 'campaignManagementCreate', value: '/app/campanas/0' },
  { key: 'campaignManagementList', value: '/app/campanas/consulta' },
  { key: 'campaignManagementEdit', value: '/app/campanas/:id' },
  { key: 'campaignManagementSee', value: '/app/campanas/:id/ver' },

  {key: 'protocolManagementCreate', value: '/app/protocol/0'},
  {key: 'protocolManagementList', value: '/app/protocol/consulta'},
  {key: 'protocolManagementEdit', value: '/app/protocol/:id'},
  {key: 'protocolManagementSee', value: '/app/protocol/:id/ver'},

  { key: 'approachManagementCreate', value: '/app/propuestas/0' },
  { key: 'approachManagementList', value: '/app/propuestas/consulta' },
  { key: 'approachManagementSee', value: '/app/propuestas/:id/ver' },

  { key: 'infringementManagementList', value: '/app/infracciones/consulta' },

  { key: 'profileManagementCreate', value: '/app/profile/0' },
  { key: 'profileManagementList', value: '/app/profile' },
  { key: 'profileManagementEdit', value: '/app/profile/:id' },

  { key: 'entityManagementCreate', value: '/app/entity/0' },
  { key: 'entityManagementList', value: '/app/entity' },
  { key: 'entityManagementEdit', value: '/app/entity/:id' },
  { key: 'entityTypeManagementList', value: '/app/entity/tipo/consulta' },
  { key: 'entityTypeManagementCreate', value: '/app/entity/tipo/0' },
  { key: 'entityTypeManagementEdit', value: '/app/entity/tipo/:id' },

  { key: 'moduleManagementCreate', value: '/app/module/0' },
  { key: 'moduleManagementList', value: '/app/module' },
  { key: 'moduleManagementEdit', value: '/app/module/:id' },
  { key: 'moduleTypeManagementList', value: '/app/module/tipo/consulta' },
  { key: 'moduleTypeManagementCreate', value: '/app/module/tipo/0' },
  { key: 'moduleTypeManagementEdit', value: '/app/module/tipo/:id' },
];

@NgModule({
  imports: [NamedRoutesModule.forRoot(routes)],
  exports: [NamedRoutesModule],
})
export class NamedRoutesConfig {
}
