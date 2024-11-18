import {Injectable} from '@angular/core';
import {AuthStorage} from '@base/shared/security/auth-storage';
import {AuthManagerService} from "@base/shared/security";
import {catchError, Observable, of, switchMap, throwError} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUser: any;

  constructor(
    private authManager: AuthManagerService) {
  }

  autoLogout() {
    setTimeout(async () => {
      await this.authManager.attemptSignOut('login');
    }, 5000);
  }

  refreshToken(): Observable<string> {
    return this.authManager.refreshToken()
      .pipe(switchMap((e) => {
        return of(e);
      }), catchError((e) => {
        this.autoLogout()
        return throwError(() => new Error(`Error`));
      }))
  }

  public hasRole(role: string): boolean {
    this.currentUser = AuthStorage.getUserSession();
    return this.currentUser && this.currentUser.roles.includes(role);
  }
}
