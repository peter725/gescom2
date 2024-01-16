import {Inject, Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, CanLoad, Route, Router} from '@angular/router';
import {AuthContextService, AUTHORIZATION_OPTIONS, AuthorizationConfig, ResourceAccessKey} from '@libs/security';


/**
 * Guard that checks the access to a given path in the app.
 *
 * To enable, configure Route with these options
 * <pre>
 *   canActivate: [CanAccessGuard],
 *   canActivateChild: [CanAccessGuard],
 *   canLoad: [CanAccessGuard],
 * </pre>
 *
 * Then specify the required ResourceAccessKey in the route data.requireAccess.
 * <pre>
 * data: {
 *   requireAccess: 'RCatBrowser'
 * }
 * </pre>
 */
@Injectable({providedIn: 'root'})
export class CanAccessGuard implements CanActivate, CanActivateChild, CanLoad {
  constructor(
    private context: AuthContextService,
    private router: Router,
    @Inject(AUTHORIZATION_OPTIONS) private config: AuthorizationConfig
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot): Promise<boolean> {
    return this.checkAccessToRoute(route);
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot): Promise<boolean> {
    return this.checkAccessToRoute(childRoute);
  }

  canLoad(route: Route): Promise<boolean> {
    return this.checkAccessToRoute(route);
  }

  private async checkAccessToRoute(route: ActivatedRouteSnapshot | Route): Promise<boolean> {
    if (!route.data) return true;

    const accessKey: ResourceAccessKey | undefined = route.data['requireAccess'];
    if (!accessKey) return true;

    const canAccess = this.context.instant().hasModule(accessKey);

    if (!canAccess) {
      const queryParams = route instanceof ActivatedRouteSnapshot ? route.queryParams : {};
      const {unauthorized} = this.config.redirect;
      await this.router.navigate([unauthorized], {queryParams});
    }
    return canAccess;
  }

}
