import {Injectable} from '@angular/core';
import {ErrorHTTPService} from '@base/shared/components/error-http/error-http.service';
import {AuthStorage} from '@base/shared/security/auth-storage';
import {AuthManagerService} from "@base/shared/security";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUser: any;

  constructor(
    private errorService: ErrorHTTPService,
    private authManager: AuthManagerService) {
  }

  autoLogout() {
    this.errorService.showAlert(401, 5);
    setTimeout(async () => {
      await this.authManager.attemptSignOut('login');
    }, 5000);
  }

  refreshToken(): Observable<string> {
    return this.authManager.refreshToken()
      .pipe(catchError((e) => {
        this.autoLogout()
        return throwError(() => new Error(`Error`));
      }))
  }

  public hasRole(role: string): boolean {
    this.currentUser = AuthStorage.getUserSession();
    return this.currentUser && this.currentUser.roles.includes(role);
  }
}
