import { registerLocaleData } from '@angular/common';
import { LOCALE_ID, NgModule, Provider } from '@angular/core';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import localeEs from '@angular/common/locales/es';


export const LOCALE_ID_PROVIDER: Provider = {
  provide: LOCALE_ID,
  useValue: 'es',
};

export const LOCALE_DATE_PROVIDER: Provider = {
  provide: MAT_DATE_LOCALE,
  useValue: 'es'
};

registerLocaleData(localeEs);

@NgModule({
  providers: [
    LOCALE_ID_PROVIDER,
    LOCALE_DATE_PROVIDER,
  ]
})
export class LocaleConfig {}
