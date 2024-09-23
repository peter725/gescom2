import { Injectable } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Params, Router } from '@angular/router';
import { AppQuery, AppQuerySource } from '@libs/commons';
import { BehaviorSubject, filter } from 'rxjs';
import { DEFAULT_FILTER_KEY } from './constants';
import { AppliedFilter, FilterOptions, FilterParamsOptions, SearchMode } from './models';
import { Query } from './query';


@Injectable({ providedIn: 'root' })
export class FilterService {

  private readonly SHOW_DELETED_KEY = 'showDeleted';
  private readonly source = new BehaviorSubject<AppliedFilter | undefined>(undefined);
  private readonly showDeletedSubject = new BehaviorSubject<boolean>(
    sessionStorage.getItem(this.SHOW_DELETED_KEY) === 'true'
  );
  private skipNextRouteParamsChange = false;

  showDeleted$ = this.showDeletedSubject.asObservable();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.listenToRouteQueries();
  }

  set(options: FilterOptions) {
    const filter: AppliedFilter = {
      name: options.name || DEFAULT_FILTER_KEY,
      mode: options.mode || SearchMode.FILTER,
      query: new Query(options.source),
    };
    this.source.next(filter);
    if (options.useParams) {
      this.skipNextRouteParamsChange = true;
      this.setRouteParams(filter.query, options.useParams);
    }
  }

  get(name: string) {
    const acceptedKeys = [name, DEFAULT_FILTER_KEY];
    return this.source.asObservable().pipe(
      filter(source => !!source && acceptedKeys.includes(source.name)),
      filter(Boolean)
    );
  }

  instant(name: string): AppliedFilter | undefined {
    const acceptedKeys = [name, DEFAULT_FILTER_KEY];
    const value = this.source.value;
    if (value && acceptedKeys.includes(value.name)) {
      return value;
    }
    return undefined;
  }

  setShowDeleted(value: boolean): void {
    this.showDeletedSubject.next(value);
    sessionStorage.setItem(this.SHOW_DELETED_KEY, JSON.stringify(value));
  }

  getShowDeleted() {
    return this.showDeletedSubject.getValue();

  }

  /**
   * Clears all stored queries
   */
  clear() {
    const nav: NavigationExtras = {
      queryParams: {},
      relativeTo: this.route,
      queryParamsHandling: ''
    };
    this.router.navigate([], nav);
  }

  private listenToRouteQueries() {
    this.route.queryParams.subscribe({
      next: params => this.routeParamsChanged(params),
      error: e => console.error(`Route query param error`, e),
    });
  }

  private routeParamsChanged(value: Params) {
    if (this.skipNextRouteParamsChange) {
      this.skipNextRouteParamsChange = false;
      return;
    }

    const source: AppQuerySource = {};
    Object.keys(value).forEach(key => source[key] = value[key] || undefined);
    this.set({
      name: DEFAULT_FILTER_KEY,
      source
    });
  }

  private setRouteParams(query: AppQuery, options: FilterParamsOptions): void {
    const queryParams = query.getSource();
    const { handling } = options;
    const nav: NavigationExtras = {
      queryParams,
      relativeTo: this.route,
      queryParamsHandling: handling
    };
    this.router.navigate([], nav);
  }
}
