import {Pipe, PipeTransform} from '@angular/core';
import {Params} from '@angular/router';
import {NamedRoutes} from './named-routes';


@Pipe({ name: 'namedRoute' })
export class NamedRoutePipe implements PipeTransform {

  private prevKey: string | undefined;
  private prevResult: string[] = [];

  constructor(private routes: NamedRoutes) {
  }

  /**
   * Returns the requested route.
   * If the route is not defined it will return an empty array.
   */
  transform(key?: string, params?: Params): string[] {
    if (!key) {
      return [];
    }
    if (key === this.prevKey) {
      return this.prevResult;
    }

    this.prevKey = key;
    let result: string[] = []
    try {
      result = this.routes.getRoute(key, params);
      this.prevResult = result;
    } catch (e) {
      result = [];
    }
    return result;
  }

}
