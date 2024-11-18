import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { gescoAuthAPI } from '@base/config/app';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { AuthStorage } from './auth-storage';
import { AuthService } from '@base/services/authService';
import { AuthDataResponse } from '@libs/sdk/auth';


@Injectable({ providedIn: 'root' })
export class HttpAuthInterceptorService implements HttpInterceptor {

  // constructor(
  //   private authManager: AuthService) {
  // }

  constructor(private readonly injector: Injector) {}

  /**
   * Lista de rutas que no deben tener la cabecera de autorización
   */
  private readonly excludedPaths: string[] = [
    `${ gescoAuthAPI.apiPath }/auth/user/request`,`${ gescoAuthAPI.srvPath }/oauth/token`
  ];

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const auth = AuthStorage.getUserAuth();
    const requiresAuth = !this.excludedPaths.includes(req.url);

    if (auth?.access_token && requiresAuth) {
      const token = `Bearer ${ auth.access_token }`;
      const headers = req.headers.set('Authorization', token);
      return next.handle(req.clone({ headers })).pipe(
        catchError((error) => {
          if (
            error instanceof HttpErrorResponse &&
            error.status === 500 &&
            error.error.message.includes('Token has expired')
          ) {
            return this.handle500Error(req, next, auth);
          }
  
          return throwError(() => error);
        })
      );
    }

    return next.handle(req);
  }

  private handle500Error(request: HttpRequest<any>, next: HttpHandler, auth: AuthDataResponse | undefined) {
    console.log("ERROR, MUST REFRESH TOKEN");
    const authManager: AuthService = this.injector.get(AuthService);

    // let newToken = '';
    return authManager.refreshToken()
    // .subscribe({
    //   next: (result) => {
    //     newToken += result;
    //     // return next.handle(request);
    //   }, complete: () => {
    //     console.log('AA');
    //     console.log(newToken);
    //     const headers = request.headers.set('Authorization', newToken);
    //     return next.handle(request.clone({ headers }));
    //   }
      
    // });
    .pipe(
      switchMap((token: any) => {
        console.log("NEW TOKEN: " + token)
        const headers = request.headers.set('Authorization', token);
        return next.handle(request.clone({ headers }));
      }),
      catchError((err) => {
        return throwError(() => err);
      })
    );

    // return next.handle(request);
    
  }

}
