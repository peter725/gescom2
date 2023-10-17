import { HttpClient } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';


export const translateLoaderFactory = (http: HttpClient) => {
  const path = './assets/i18n/';
  const format = '.json';
  return new TranslateHttpLoader(http, path, format);
};

@NgModule({
  imports: [TranslateModule.forRoot({
    defaultLanguage: 'es',
    loader: {
      provide: TranslateLoader,
      useFactory: (translateLoaderFactory),
      deps: [HttpClient],
    }
  })],
  exports: [TranslateModule]
})
export class TranslateConfig {
}
