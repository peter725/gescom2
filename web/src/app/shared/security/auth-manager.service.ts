import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { appOrigin } from '@base/config/app';
import { HiddenFormData } from '@base/shared/components/form';
import { NotificationService } from '@base/shared/notification';
import { AppError, ComponentStatus } from '@libs/commons';
import { CRUD_OPERATIONS, CrudOperationStorage } from '@libs/crud-api';
import { NamedRoutes } from '@libs/named-routes';
import {
  AuthDataResponse,
  AuthUserDetails,
  ClaveRequestFormData,
  UserSignInPetition,
  UserSignInRequest
} from '@libs/sdk/auth';
import { AuthContextService } from '@libs/security';
import { BehaviorSubject, firstValueFrom } from 'rxjs';
import { take } from 'rxjs/operators';
import { AuthStorage } from './auth-storage';
import { GAuthSubject } from './auth-subject';
import { AuthProcessResultParam, AuthProcessState } from './models';


@Injectable({ providedIn: 'root' })
export class AuthManagerService {

  private readonly processMessage = new BehaviorSubject('');
  public readonly processStatus = new ComponentStatus<AuthProcessState>('PROCESS');

  constructor(
    private authContext: AuthContextService<GAuthSubject>,
    private namedRoutes: NamedRoutes,
    private router: Router,
    private route: ActivatedRoute,
    private notification: NotificationService,
    private http: HttpClient,
    @Inject(CRUD_OPERATIONS) private operations: CrudOperationStorage,
  ) {
    this.monitorAuthExpiry();
  }

  get processMessage$() {
    return this.processMessage.asObservable();
  }

  // Eliminar en producción
  async fakeLogin(docNum: string, secret: string) {
    try {
      this.updateProcess('PROCESS', 'Completando el proceso de autenticación');
      const url = this.operations.get('fakeLogIn').base().path;
      const authentication = await firstValueFrom(this.http.post<AuthDataResponse>(url, { docNum, secret }));

      // Solicitar los datos del usuario autenticado.
      this.updateProcess('PROCESS', 'Recuperando los datos del usuario');
      const authDetailsUrl = this.operations.get('authDetails').base().path;
      const authDetailsHeader = new HttpHeaders({ Authorization: authentication.token });
      const authDetails = await firstValueFrom(this.http.get<AuthUserDetails>(authDetailsUrl, { headers: authDetailsHeader }));

      // Limpiamos los datos del almacén
      AuthStorage.clearTempAuth();
      AuthStorage.saveUserAuth(authentication);

      // Autorizamos al usuario autenticado
      this.authContext.authorize(new GAuthSubject(authDetails, authentication.exp));

      // Registrar un timeout para eliminar el token caducado automáticamente
      this.updateProcess('DONE', 'Autenticación realizada con éxito, redirigiendo');
      await this.router.navigate(this.namedRoutes.getRoute('home'));
    } catch (e) {
      const err = AppError.parse(e);
      const message = err.message || 'Login falso erróneo';
      this.updateProcess('ERROR', message);
      throw new Error(message);
    }
  }

  /**
   * Inicializa la autenticación de la aplicación si disponemos del token de usuario.
   */
  async initAuthentication() {
    const userAuth = AuthStorage.getUserAuth();
    if (userAuth) {
      try {
        const operation = this.operations.get('authDetails').base({}).path;
        const headers = new HttpHeaders({ Authorization: userAuth.token });
        const details = await firstValueFrom(this.http.get<AuthUserDetails>(operation, { headers }));
        this.authContext.authorize(new GAuthSubject(details, userAuth.exp));
        return Promise.resolve();
      } catch (e) {
        console.error(e);
        AuthStorage.clearUserAuth();
      }
    }
    return Promise.resolve();
  }

  /**
   * Inicializa el control de estado del proceso de autenticación.
   */
  async initProcess() {
    this.updateProcess('PROCESS', 'Inicializando');
    // Si el usuario está autenticado, forzamos la compleción del proceso.
    if (this.authContext.instant().isAuthenticated()) {
      this.updateProcess('DONE');
      await this.completeProcess();
      return;
    }

    const tempAuth = AuthStorage.getTempAuth();
    const queryParams = await firstValueFrom(this.route.queryParamMap.pipe(take(1)));
    const resultStatus = queryParams.get(AuthProcessResultParam.STATUS_PARAM);
    const resultMessage = queryParams.get(AuthProcessResultParam.STATUS_MESSAGE);
    const resultCode = queryParams.get(AuthProcessResultParam.STATUS_CODE);

    // Si disponemos de un token temporal, significa que el proceso ha sido
    // inicializado y debemos completarlo en función del resultado recibido
    // por los parámetros de query.
    if (tempAuth) {
      try {
        await this.attemptSignIn();
        await this.completeProcess();
      } catch (e) {
        // ignored
        const err = AppError.parse(e);
        const message = resultMessage || err.message || 'No ha sido posible completar el proceso de autenticación.';
        this.updateProcess('ERROR', message);
      }

      // De momento no aplica por culpa del servidor de despliegue
      // if (resultStatus === AuthProcessResultValues.OK) {
      // Si el valor es OK significa que podemos completar el proceso de
      // login en TULSA.
      // await this.attemptSignIn();
      // await this.completeProcess();
      // } else {
      // En caso contrario puede que haya ocurrido un error en el proceso
      // o que no tengamos acceso a la aplicación. resultMessage nos debería
      // proporcionar el motivo correspondiente.
      // const message = resultMessage || 'No ha sido posible completar el proceso de autenticación.';
      // this.updateProcess('ERROR', message);
      // }
      return;
    }

    // Si no cumplimos ninguna de las condiciones anteriores, significa que
    // no se ha inicializado el proceso y el usuario no está autenticado.
    this.updateProcess('INIT');
  }

  /**
   * Solicita los datos necesarios para inicializar el proceso de SignIn.
   */
  async requestSignIn() {
    try {
      this.updateProcess('PROCESS', 'generic.actions.loggingIn');

      // Obtenemos la ruta de login y la transformamos en un string. La tranformación
      // incluye un "/" demás.
      const path = this.namedRoutes.getRoute('login').join('/').replace('//', '/');
      const payload: UserSignInRequest = {
        processReturnUrl: appOrigin + path
      };
      const loginRequestUrl = this.operations.get('authRequest').base().path;
      const loginOperation = this.http.post<UserSignInPetition>(loginRequestUrl, payload);
      const response = await firstValueFrom(loginOperation);

      // Almacenar los datos relevantes para la autenticación temporal
      AuthStorage.saveTempAuth(response);

      const form = this.buildClaveFormData(response.authData);
      this.updateProcess('PROCESS', 'text.other.redirectToClave');
      return form;
    } catch (e) {
      const err = AppError.parse(e);
      console.error(err);
      const message = err.message || 'text.err.loginFailed';
      this.notification.show({
        type: 'danger',
        message,
        ttl: 10_000,
      });
      this.updateProcess('ERROR', message);
      throw new Error(message);
    }
  }

  /**
   * Realizar el proceso de SignIn y registra el usuario en el contexto de seguridad.
   */
  async attemptSignIn() {
    try {
      // Comprobar si existe una autenticación temporal
      const tempAuth = AuthStorage.getTempAuth();
      if (!tempAuth) {
        // Si no existe, redirigir a login regular
        this.updateProcess('INIT');
        return;
      }

      // Intentamos realizar login en el sistema utilizando la autorización temporal.
      this.updateProcess('PROCESS', 'Completando el proceso de autenticación');
      const logInUrl = this.operations.get('authLogIn').base().path;
      const logInReq = this.http.post<AuthDataResponse>(logInUrl, tempAuth);
      const authentication = await firstValueFrom(logInReq);

      // Solicitar los datos del usuario autenticado.
      this.updateProcess('PROCESS', 'Recuperando los datos del usuario');
      const authDetailsUrl = this.operations.get('authDetails').base().path;
      const authDetailsHeader = new HttpHeaders({ Authorization: authentication.token });
      const authDetails = await firstValueFrom(this.http.get<AuthUserDetails>(authDetailsUrl, { headers: authDetailsHeader }));

      // Limpiamos los datos del almacén
      AuthStorage.clearTempAuth();
      AuthStorage.saveUserAuth(authentication);

      // Autorizamos al usuario autenticado
      this.authContext.authorize(new GAuthSubject(authDetails, authentication.exp));

      // Registrar un timeout para eliminar el token caducado automáticamente
      this.updateProcess('DONE', 'Autenticación realizada con éxito, redirigiendo');
    } catch (e) {
      // En caso de cualquier otro error, redirigir a login con error
      console.error(e);
      const err = AppError.parse(e);
      const message = err.message || 'Ha ocurrido un error en el proceso de autenticación';
      this.updateProcess('ERROR', message);
      throw new Error(message);
    }
  }

  /**
   * Solicita los datos necesarios para inicializar el proceso de SignOut.
   */
  async requestSignOut() {
    try {
      const userAuth = AuthStorage.getUserAuth();
      if (!userAuth) return;

      const url = this.operations.get('authLogOut').base().path;
      const headers = new HttpHeaders({ Authorization: userAuth.token });
      const response = await firstValueFrom(this.http.post<ClaveRequestFormData>(url, {}, { headers }));
      return this.buildClaveFormData(response);
    } catch (e) {
      const err = AppError.parse(e);
      const message = err.message || 'text.err.logoutFailed';
      this.notification.show({
        type: 'danger',
        message,
        ttl: 10_000,
      });
      throw new Error(err.message);
    }
  }

  /**
   * Realiza el proceso de SignOut.
   * @param opts.redirectToPage override redirect location (default login)
   * @param opts.redirectTimeout override logout wait time (default 1500ms)
   */
  async attemptSignOut(opts?: {
    redirectToPage?: string,
    redirectTimeout?: number,
  }) {
    this.authContext.clear();
    AuthStorage.clearTempAuth();
    AuthStorage.clearUserAuth();
    const redirectToPage = opts?.redirectToPage || 'login';
    const redirectTimeout = opts?.redirectTimeout || 1500;
    setTimeout(async () => {
      await this.router.navigate(this.namedRoutes.getRoute(redirectToPage));
    }, redirectTimeout);
  }

  /**
   * Completa el proceso de SignIn y redirige el usuario a la página principal.
   */
  async completeProcess() {
    const isCompleted = this.processStatus.is('DONE');
    const isAuthenticated = this.authContext.instant().isAuthenticated();
    if (isCompleted && isAuthenticated) {
      await this.router.navigate(this.namedRoutes.getRoute('home'));
      this.updateProcess('INIT');
    }
  }

  private updateProcess(process: AuthProcessState, message?: string) {
    this.processStatus.status = process;
    this.processMessage.next(message || '');
  }

  /**
   * Genera la estructura de datos necesaria para realizar la llamada al
   * servicio de clave.
   */
  private buildClaveFormData(data: ClaveRequestFormData): HiddenFormData {
    const { authUrl, authParams } = data;
    const params: { key: string, value: string }[] = [];
    Object.entries(authParams).forEach(([key, value]) => params.push({ key, value }));
    return {
      action: authUrl,
      method: 'POST',
      params,
    };
  }

  private monitorAuthExpiry() {
    this.authContext.authWillExpireIn().subscribe(expIn => {
      const remainingMin = Math.ceil(expIn / 1000 / 60);
      this.notification.show({
        message: 'text.other.authWillExpIn',
        messageParams: { remainingMin },
      }, { ttl: 10 });
    });

    this.authContext.authHasExpired().subscribe(async () => {
      this.notification.show({ message: 'text.other.authHasExpired' });
      try {
        await this.requestSignOut();
      } finally {
        await this.attemptSignOut();
      }
    });
  }
}
