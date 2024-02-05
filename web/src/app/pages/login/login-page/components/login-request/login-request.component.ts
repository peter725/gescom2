import {Component, OnDestroy} from '@angular/core';
import {isDevEnvironment, gescoAuthAPI} from '@base/config/app';
import {HiddenFormData} from '@base/shared/components/form';
import {AuthManagerService, AuthProcessState} from '@base/shared/security';
import {AuthStorage} from '@base/shared/security/auth-storage';
import {ComponentStatus} from '@libs/commons';
import {filter, Observable, ReplaySubject, takeUntil} from 'rxjs';
import {ActivatedRoute, Router} from "@angular/router";
import {NamedRoutes} from '@libs/named-routes/named-routes';


@Component({
  selector: 'tsw-login-request',
  templateUrl: './login-request.component.html',
})
export class LoginRequestComponent implements OnDestroy {

  readonly status: ComponentStatus<AuthProcessState>;
  readonly processMessage$: Observable<string>;

  authForm: HiddenFormData | undefined;

  // Elementos necesarios solamente para el entorno de desarrollo
  // para saltarse clave
  isDevEnvironment = isDevEnvironment;
  fakeLogin = false;
  gcForm: HiddenFormData | undefined;

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(private authManager: AuthManagerService,
              protected router: Router,
              private routes: NamedRoutes,
              protected route: ActivatedRoute,) {
    this.status = authManager.processStatus;
    this.processMessage$ = authManager.processMessage$;
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  async requestLogin() {
    try {
      this.authForm = await this.authManager.requestSignIn();
    } catch (e) {
      // ignored
    }
  }

  // Gracias cl@ve
  // Eliminar al poner en producción


  async doLogin(nif: string, pass: string) {
    try {
      await this.authManager.login(nif, pass);
    } catch (e) {
      this.fakeLogin = false;
    }
  }
}
