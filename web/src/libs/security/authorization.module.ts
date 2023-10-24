import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule, Provider } from '@angular/core';
import { CanAccessDirective, IsAuthenticatedDirective, IsSameSubjectDirective } from './directives';


const EXPORTED_DECLARATIONS = [
  CanAccessDirective,
  IsAuthenticatedDirective,
  IsSameSubjectDirective
];

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS
})
export class AuthorizationModule {
  static forRoot(authorizationConfigProvider: Provider, aclConfigProvider: Provider): ModuleWithProviders<AuthorizationModule> {
    return {
      ngModule: AuthorizationModule,
      providers: [
        authorizationConfigProvider,
        aclConfigProvider
      ]
    };
  }
}
