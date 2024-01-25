import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { appOrigin, gescoAuthAPI } from '@base/config/app';
import { HiddenFormData } from '@base/shared/components/form';
import { NotificationService } from '@base/shared/notification';
import { AppError, ComponentStatus } from '@libs/commons';
import { CRUD_OPERATIONS, CrudOperationStorage } from '@libs/crud-api';
import { NamedRoutes } from '@libs/named-routes';
import {
  AuthDataResponse,
  AuthUserDetails,
  ClaveRequestFormData,
  UserSignInRequest
} from '@libs/sdk/auth';
import { AuthContextService } from '@libs/security';
import { BehaviorSubject, firstValueFrom } from 'rxjs';
import { take } from 'rxjs/operators';
import { AuthStorage } from './auth-storage';
import { AppAuthSubject } from './auth-subject';
import { AuthProcessResultParam, AuthProcessResultValues, AuthProcessState } from './models';
import { TokenStorageService } from '@base/services/tokenStorageService';


@Injectable({ providedIn: 'root' })
export class AuthManagerService {

  private readonly processMessage = new BehaviorSubject('');
  public readonly processStatus = new ComponentStatus<AuthProcessState>('PROCESS');

  constructor(
    private authContext: AuthContextService<AppAuthSubject>,
    private namedRoutes: NamedRoutes,
    private router: Router,
    private route: ActivatedRoute,
    private notification: NotificationService,
    private http: HttpClient,
    private tokeStorageService: TokenStorageService,
    @Inject(CRUD_OPERATIONS) private operations: CrudOperationStorage,
  ) {
  }

  get processMessage$() {
    return this.processMessage.asObservable();
  }

  // Eliminar en producción
  async login(docNum: string, secret: string) {
    try {
      this.updateProcess('PROCESS', 'Completando el proceso de autenticación');
      const authentication = await this.getLoginToken(docNum, secret);
      console.log('authentication', authentication);
      // Solicitar los datos del usuario autenticado.
      this.updateProcess('PROCESS', 'Recuperando los datos del usuario');
      const authDetails = await this.getUserInfo(authentication);
      // Limpiamos los datos del almacén
      AuthStorage.saveUserAuth(authentication);
      // Autorizamos al usuario autenticado
      this.authContext.authorize(new AppAuthSubject(authDetails));
      // Registrar un timeout para eliminar el token caducado automáticamente
      this.updateProcess('DONE', 'Autenticación realizada con éxito, redirigiendo');
      await this.router.navigate(this.namedRoutes.getRoute('home'));
    } catch (e) {
      const err = AppError.parse(e);
      const message = err.message || 'Datos de accesos incorrectos';
      this.updateProcess('ERROR', message);
      throw new Error(message);
    }
  }

  hardCodeLogin(docNum: string, secret: string) {
    if (docNum === '12345678A' && secret === 'admin') {
      const user = {
        usuario: 'Usuario Interno',
        roles: ['interna']
      }
      this.tokeStorageService.saveUser(user);
      this.router.navigate(this.namedRoutes.getRoute('home'));
    } else if (docNum === '87654321A' && secret === 'admin') {
      const user = {
        usuario: 'Usuario Solicitante',
        roles: ['externa']
      }
      this.tokeStorageService.saveUser(user);
      this.router.navigate(this.namedRoutes.getRoute('home'));
    } else {
      this.updateProcess('ERROR', 'Datos de accesos incorrectos');
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
        const headers = new HttpHeaders({Authorization: userAuth.access_token});
        const details = await firstValueFrom(this.http.get<AuthUserDetails>(operation, {headers}));
        this.authContext.authorize(new AppAuthSubject(details));
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
    console.log('entra initProcess');
    this.updateProcess('PROCESS', 'Inicializando');
    // Si el usuario está autenticado, forzamos la compleción del proceso.
    if (this.authContext.instant().isAuthenticated()) {
      this.updateProcess('DONE');
      await this.completeProcess();
      return;
    }

    const queryParams = await firstValueFrom(this.route.queryParamMap.pipe(take(1)));
    console.log('queryParams', queryParams);
    const resultStatus = queryParams.get(AuthProcessResultParam.STATUS_PARAM);
    const resultMessage = queryParams.get(AuthProcessResultParam.STATUS_MESSAGE);
    const resultCode = queryParams.get(AuthProcessResultParam.AUTH_CODE)!;
    console.log('resultStatus', resultStatus);
    console.log('resultMessage', resultMessage);
    console.log('resultCode', resultCode);
    // Si disponemos de un token temporal, significa que el proceso ha sido
    // inicializado y debemos completarlo en función del resultado recibido
    // por los parámetros de query.
    if (resultStatus === AuthProcessResultValues.OK) {
      try {
        await this.signIn(resultCode);
        await this.completeProcess();
      } catch (e) {
        // ignored
        const err = AppError.parse(e);
        const message = resultMessage || err.message || 'No ha sido posible completar el proceso de autenticación.';
        this.updateProcess('ERROR', message);
      }
      return;
    } else if (resultStatus === AuthProcessResultValues.KO) {
      const message = resultMessage || 'No ha sido posible completar el proceso de autenticación.';
      this.updateProcess('ERROR', message);
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
      console.log('requestSignIn');

      // Obtenemos la ruta de login y la transformamos en un string. La tranformación
      // incluye un "/" demás.
      const path = this.namedRoutes.getRoute('login').join('/').replace('//', '/');
      const payload: UserSignInRequest = {
        processReturnUrl: appOrigin + path
      };
      const loginRequestUrl = this.operations.get('authRequest').base().path;
      const loginOperation = this.http.post<ClaveRequestFormData>(loginRequestUrl, payload);
      const response = await firstValueFrom(loginOperation);
      return this.buildClaveFormData(response);
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
  async signIn(code: string) {
    try {
      console.log('Ento a signIn');
      const auth = {authorizationCode: code}
      // Intentamos realizar login en el sistema utilizando la autorización temporal.
      this.updateProcess('PROCESS', 'Completando el proceso de autenticación');
      const logInUrl = this.operations.get('authLogIn').base().path;
      const logInReq = this.http.post<AuthDataResponse>(logInUrl, auth);
      const authentication = await firstValueFrom(logInReq);

      // Solicitar los datos del usuario autenticado.
      this.updateProcess('PROCESS', 'Recuperando los datos del usuario');
      const authDetails = await this.getUserInfo(authentication);

      // Limpiamos los datos del almacén
      AuthStorage.saveUserAuth(authentication);

      // Autorizamos al usuario autenticado
      this.authContext.authorize(new AppAuthSubject(authDetails));
      // Registrar un timeout para eliminar el token caducado automáticamente
      this.updateProcess('DONE', 'Autenticación realizada con éxito, redirigiendo');
      await this.router.navigate(this.namedRoutes.getRoute('home'));
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
      const headers = new HttpHeaders({Authorization: userAuth.access_token});
      const response = await firstValueFrom(this.http.post<ClaveRequestFormData>(url, {}, {headers}));
      //return this.buildClaveFormData(response);
      return response;
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
   * @param redirectToPage Permite especificar opcionalmente a qué página redireccionar al completar el proceso.
   */
  async attemptSignOut(redirectToPage?: string) {
    this.authContext.clear();
    AuthStorage.clearUserAuth();
    if (redirectToPage) {
      await this.router.navigate(this.namedRoutes.getRoute(redirectToPage));
    }
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
    const {authUrl, authParams} = data;
    const params: { key: string, value: string }[] = [];
    Object.entries(authParams).forEach(([key, value]) => params.push({key, value}));
    return {
      action: authUrl,
      method: 'POST',
      params,
    };
  }

  private async getUserInfo(authentication: AuthDataResponse): Promise<AuthUserDetails> {
    const authDetailsUrl = this.operations.get('authDetails').base().path;
    const authorization = this.capitalize(authentication.token_type) + " " + authentication.access_token
    const authDetailsHeader = new HttpHeaders({'Authorization': authorization});
    return firstValueFrom(this.http.get<AuthUserDetails>(authDetailsUrl, {headers: authDetailsHeader}));
  }

  private async getLoginToken(docNum: string, secret: string): Promise<AuthDataResponse> {
    const url = this.operations.get('manualLogIn').base().path;
    const headers = new HttpHeaders({
      'Authorization': gescoAuthAPI.authorization,
      'Content-Type': "application/x-www-form-urlencoded"
    });
    console.log('url', url);
    console.log('headers', headers);
    const body = new URLSearchParams();
    body.set('username', docNum);
    body.set('password', secret);
    body.set('grant_type', 'password');
    return firstValueFrom(this.http.post<AuthDataResponse>(url, body, {headers}));
  }

  capitalize(text: string) {
    return text.charAt(0).toUpperCase() + text.slice(1);
  }
}
