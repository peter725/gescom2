import { NgModule } from '@angular/core';
import { CrudModule, CrudOperationStorage, HttpOperationType, OperationsDefSrc } from '@libs/crud-api';
import { gescoAppAPI, gescoAuthAPI } from './app';


export const crudOperationsStorageFactory = () => {
  const apiOperations: OperationsDefSrc = {
    health: { type: HttpOperationType.SIMPLE, path: `/health` },
    provinces: { type: HttpOperationType.READ, path: `/provinces` },
    campaignProposal: { type: HttpOperationType.CRUD, path: `/campaign_proposal` },
    protocol: { type: HttpOperationType.CRUD, path: `/protocol` },
    phaseCampaign: { type: HttpOperationType.CRUD, path: `/phase_campaign` },
    campaign: { type: HttpOperationType.CRUD, path: `/campaign` },
    protocolFileUpload: { type: HttpOperationType.SIMPLE, path: `/document/create` },
    ambit: { type: HttpOperationType.CRUD, path: `/ambit` },
    responsibleEntity: { type: HttpOperationType.CRUD, path: `/autonomous_community` },
    campaignType: { type: HttpOperationType.CRUD, path: `/campaign_type` },
    participants: { type: HttpOperationType.CRUD, path: `/autonomous_community` },
    proponents: { type: HttpOperationType.CRUD, path: `/proponent` },
    specialists: { type: HttpOperationType.CRUD, path: `/specialist` },
    autonomousCommunity: { type: HttpOperationType.CRUD, path: '/autonomous_community', },
    infringement: { type: HttpOperationType.CRUD, path: '/infringement', },
    authority: { type: HttpOperationType.READ, path: '/authority_dgc', },
    origins: { type: HttpOperationType.READ, path: '/origins', },
    moduleTypes: { type: HttpOperationType.CRUD, path: '/module_types' },
    modules: { type: HttpOperationType.CRUD, path: '/modules' },
    fieldTypes: { type: HttpOperationType.READ, path: '/field-types' },
    fields: { type: HttpOperationType.CRUD, path: '/fields' },
    entities: { type: HttpOperationType.CRUD, path: '/entities' },
    entityTypes: { type: HttpOperationType.CRUD, path: '/entity_types' },
    elementFormats: { type: HttpOperationType.READ, path: `/element-formats` },
    users: { type: HttpOperationType.CRUD, path: '/users', },
    userType: { type: HttpOperationType.CRUD, path: '/user_type', },
    usersView: { type: HttpOperationType.READ, path: `/users/simple` },
    profiles: { type: HttpOperationType.CRUD, path: '/profiles' },
    roles: { type: HttpOperationType.CRUD, path: '/roles' },
    permissions: { type: HttpOperationType.READ, path: `/permissions` },
    fieldModules: { type: HttpOperationType.CRUD, path: '/field_modules' },
    fieldModuleAssociations: { type: HttpOperationType.CRUD, path: '/fields_modules_associations' },
    visibility: { type: HttpOperationType.SIMPLE, path: '/visibility' },
    visibilityWeb: { type: HttpOperationType.SIMPLE, path: '/visibility/web' },
    required: { type: HttpOperationType.SIMPLE, path: '/required' },
    position: { type: HttpOperationType.SIMPLE, path: '/position-fields' },
    scopes: { type: HttpOperationType.CRUD, path: '/user_scopes' },
    catalogues: { type: HttpOperationType.SIMPLE, path: `/catalogues`, },
    catHierarchy: { type: HttpOperationType.SIMPLE, path: `/catalogues/hierarchies`, },
    catTerm: { type: HttpOperationType.SIMPLE, path: `/catalogues/terms` },
    catTermAttrib: { type: HttpOperationType.SIMPLE, path: `/catalogues/termsAttributes` },
    catBaseTerm: { type: HttpOperationType.SIMPLE, path: `/catalogues/base_terms` },
    catAttributes: { type: HttpOperationType.SIMPLE, path: `/catalogues/terms` },
    catFacets: { type: HttpOperationType.SIMPLE, path: `/catalogues/facets` },
    sampleFormFields: { type: HttpOperationType.SIMPLE, path: `/samples/forms/:module/fields` },
    sampleFormValues: { type: HttpOperationType.SIMPLE, path: `/samples/forms/:module/fields/values` },
    sampleSeason: { type: HttpOperationType.CRUD, path: `/seasons` },
    sampleStates: { type: HttpOperationType.READ, path: `/sample_states` },
    samples: { type: HttpOperationType.CRUD, path: `/samples` },
    sampleFileUpload: { type: HttpOperationType.SIMPLE, path: `/samples/create_from_file` },
    sampleTemplate: { type: HttpOperationType.SIMPLE, path: `/sample_templates` },
    languages: { type: HttpOperationType.READ, path: `/languages` },
    businessRules: { type: HttpOperationType.CRUD, path: `/business_rules` },
    businessRuleDefinitions: { type: HttpOperationType.READ, path: `/business_rules/definitions` },
    infoTypes: { type: HttpOperationType.READ, path: `/info_types` },
    templateManagement: { type: HttpOperationType.CRUD, path: `/sample_templates` },
  };

  const userOperations: OperationsDefSrc = {
    authRequest: {type: HttpOperationType.SIMPLE, path: `/auth/user/request`},

    authLogOut: {type: HttpOperationType.SIMPLE, path: `/auth/user/sign_out`},
    authDetails: {type: HttpOperationType.SIMPLE, path: `/auth/user/me`},
  };

  const authOperations: OperationsDefSrc = {
    authRequest: { type: HttpOperationType.SIMPLE, path: `/auth/user/request` },
    authLogOut: { type: HttpOperationType.SIMPLE, path: `/auth/user/sign_out` },
    authDetails: { type: HttpOperationType.SIMPLE, path: `/auth/user/me` },
    fakeLogIn: { type: HttpOperationType.SIMPLE, path: `/auth/user/fake-sign-in` },
    manualLogIn: {type: HttpOperationType.SIMPLE, path: `/oauth/token`},
    authLogIn: {type: HttpOperationType.SIMPLE, path: `/auth/user/sign_in`},
  };

  return CrudOperationStorage.from([
    { baseUrl: gescoAppAPI.apiPath, operations: apiOperations },
    { baseUrl: gescoAuthAPI.apiPath, operations: authOperations },
  ]);
};


@NgModule({
  imports: [CrudModule.forRoot(crudOperationsStorageFactory)],
  exports: [CrudModule]
})
export class CrudApiConfig {
}
