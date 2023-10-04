import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Data, NavigationEnd, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { BehaviorSubject, filter } from 'rxjs';
import { Breadcrumb } from './breadcrumb.model';


@Injectable({ providedIn: 'root' })
export class BreadcrumbsService {

  private readonly crumbs = new BehaviorSubject<Breadcrumb[]>([]);

  constructor(
    private router: Router,
    private translate: TranslateService,
  ) {
    this.registerPathListener();
  }

  get breadcrumbs$() {
    return this.crumbs.asObservable();
  }

  private registerPathListener() {
    this.router.events.pipe(filter(ev => ev instanceof NavigationEnd)).subscribe(() => this.updateCrumbs());
  }

  private updateCrumbs() {
    // Construct the breadcrumb hierarchy
    const root = this.router.routerState.snapshot.root;
    const breadcrumbs: Breadcrumb[] = [
      { path: '/', title: 'Inicio', active: false },
    ];
    this.addBreadcrumb(root, [], breadcrumbs);

    // Emit the new hierarchy
    this.crumbs.next(breadcrumbs);
  }

  private addBreadcrumb(route: ActivatedRouteSnapshot, parentUrl: string[], breadcrumbs: Breadcrumb[]) {
    if (route) {
      const routeUrl = parentUrl.concat(route.url.map(url => url.path));

      // Add an element for the current route part
      const { breadcrumb } = route.data;
      if (breadcrumb) {
        const label = this.getLabel(route.data);
        this.translate.get(label).subscribe(title => breadcrumbs.push({
          title,
          path: '/' + routeUrl.join('/'),
          active: !route.firstChild,
        }));
      }

      // Add another element for the next route part
      if (route.firstChild) this.addBreadcrumb(route.firstChild, routeUrl, breadcrumbs);
    }
  }

  private getLabel(data: Data): string {
    // The breadcrumb can be defined as a static string or as a function to construct the breadcrumb element out of the route data
    const { breadcrumb } = data;
    return typeof breadcrumb === 'function' ? breadcrumb(data) : breadcrumb;
  }
}
