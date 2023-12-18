import { Inject, Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, CanLoad, Router } from '@angular/router';
import { AuthorizationConfig } from '../models';
import { AuthContextService } from '../services';
import { AUTHORIZATION_OPTIONS } from '../tokens';


@Injectable({ providedIn: 'root' })
export class IsAuthenticatedGuard implements CanActivate, CanActivateChild, CanLoad {

  constructor(
    private context: AuthContextService,
    private router: Router,
    @Inject(AUTHORIZATION_OPTIONS) private config: AuthorizationConfig
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot): Promise<boolean> {
    return this.checkAuthenticationStatus(route.queryParams);
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot): Promise<boolean> {
    return this.checkAuthenticationStatus(childRoute.queryParams);
  }

  canLoad(): Promise<boolean> {
    return this.checkAuthenticationStatus();
  }

  private async checkAuthenticationStatus(queryParams?: Record<string, unknown>): Promise<boolean> {
    const subject = this.context.instant();
    const isAuthenticated = subject.isAuthenticated();
    if (!isAuthenticated) {
      const { unauthenticated } = this.config.redirect;
      await this.router.navigate([unauthenticated], { queryParams });
    }

    return isAuthenticated;
  }
}
