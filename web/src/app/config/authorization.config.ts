import { HttpClient } from '@angular/common/http';
import { APP_INITIALIZER, NgModule, Provider } from '@angular/core';
import { AuthManagerService, AclFactory, SecurityInitFactory } from '@base/shared/security';
import { ACL_TOKEN, AUTHORIZATION_OPTIONS, AuthorizationModule } from '@libs/security';


const config: AuthorizationConfig = {
  redirect: {
    unauthenticated: '/auth/login',
    unauthorized: '/not-found'
  },
};

const AUTHORIZATION_CONFIG_PROVIDER: Provider = {
  provide: AUTHORIZATION_OPTIONS,
  useValue: config
};

const ACL_CONFIG_PROVIDER: Provider = {
  provide: ACL_TOKEN,
  useFactory: AclFactory,
  deps: [HttpClient],
};

const SECURITY_INITIALIZER: Provider = {
  provide: APP_INITIALIZER,
  useFactory: SecurityInitFactory,
  deps: [AuthManagerService],
  multi: true,
};

@NgModule({
  imports: [AuthorizationModule.forRoot(AUTHORIZATION_CONFIG_PROVIDER, ACL_CONFIG_PROVIDER)],
  exports: [AuthorizationModule],
  providers: [
    SECURITY_INITIALIZER
  ],
})
export class AuthorizationConfig {
}
