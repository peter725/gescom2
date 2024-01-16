import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NamedRoutesConfig } from '@base/config/named-routes.config';
import { HttpClientConfig } from '@base/config/http-client.config';
import { routes } from './pages/routes';
import { HttpClientModule } from '@angular/common/http';
import { CrudApiConfig } from '@base/config/crud-api.config';
import { TranslateConfig } from '@base/config/translate.config';
import { TitleConfig } from '@base/config/title.config';
import { ConfirmationModule } from '@base/shared/confirmation';
import { NotificationModule } from '@base/shared/notification';
import { FormConfig } from '@base/config/form.config';
import { LocaleConfig } from '@base/config/locale.config';
import { AuthorizationConfig } from '@base/config/authorization.config';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    RouterModule.forRoot(routes, { initialNavigation: 'enabledNonBlocking' }),
    NamedRoutesConfig,
    HttpClientConfig,
    HttpClientModule,
    CrudApiConfig,
    TranslateConfig,
    TitleConfig,
    ConfirmationModule.forRoot(),
    NotificationModule.forRoot(),
    FormConfig,
    LocaleConfig,
    AuthorizationConfig
  ],
  declarations: [
    AppComponent,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
