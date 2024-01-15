import { Params } from '@angular/router';
import { NamedRoutesStorage } from './models';
import { Inject, Injectable } from '@angular/core';
import { APP_ROUTES } from './tokens';


@Injectable({ providedIn: 'root' })
export class NamedRoutes {

  constructor(@Inject(APP_ROUTES) private readonly routes: NamedRoutesStorage) {
  }

  /**
   * Returns the route from the storage
   * @return Route value
   * @throws Key not found
   */
  getRoute(key: string, params?: Params): string[] {
    // Get the route from storage
    if (!this.routes.has(key)) {
      return [key];
    }
    let baseRoute = this.routes.get(key) + '';
    if (params) {
      Object.keys(params).forEach(key => baseRoute = baseRoute.replace(`:${ key }`, params[key]));
    }
    const val = baseRoute.split('/');
    val.unshift('/');
    return val;
  }
}
