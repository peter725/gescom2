import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector, Provider } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';


@Injectable()
export class LocaleConfigurationService implements HttpInterceptor {
  private translateService: TranslateService | undefined;

  constructor(
    private injector: Injector,
  ) {
    setTimeout(() => this.translateService = injector.get(TranslateService));
  }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const lang = this.translateService?.currentLang || this.translateService?.defaultLang || 'es';
    const headers = req.headers.set('Accept-Language', lang);
    return next.handle(req.clone({ headers }));
  }
}

export const REQUEST_LOCALE_PROVIDER: Provider = {
  provide: HTTP_INTERCEPTORS,
  multi: true,
  useClass: LocaleConfigurationService,
};
