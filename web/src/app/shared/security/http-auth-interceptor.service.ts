import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { gescoAuthAPI } from '@base/config/app';
import { Observable } from 'rxjs';
import { AuthStorage } from './auth-storage';


@Injectable({ providedIn: 'root' })
export class HttpAuthInterceptorService implements HttpInterceptor {
  /**
   * Lista de rutas que no deben tener la cabecera de autorización
   */
  //private readonly excludedPaths: string[] = [
    //`${ gescoAuthAPI.apiPath }/auth/user/request`,
  //];

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    //const auth = AuthStorage.getUserAuth();
    //onst requiresAuth = !this.excludedPaths.includes(req.url);

    //if (auth?.token && requiresAuth) {
      //const token = `Bearer ${ auth.token }`;
      //const headers = req.headers.set('Authorization', token);
      //return next.handle(req.clone({ headers }));
    //}

    return next.handle(req);
  }

}
