import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule, Provider } from '@angular/core';

import { REQUEST_LOCALE_PROVIDER } from './locale-configuration.service';


const REQUEST_AUTH_PROVIDER: Provider = {
  provide: HTTP_INTERCEPTORS,
  multi: true,
  useExisting: null
};

@NgModule({
  imports: [HttpClientModule],
  exports: [HttpClientModule],
  providers: [
    REQUEST_AUTH_PROVIDER,
    REQUEST_LOCALE_PROVIDER,
  ]
})
export class HttpClientConfig {}
