import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Breadcrumb } from './breadcrumb.model';
import { BreadcrumbsService } from './breadcrumbs.service';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-breadcrumbs',
  templateUrl: './breadcrumbs.component.html',
  styleUrls: ['./breadcrumbs.component.scss'],
})
export class BreadcrumbsComponent {

  breadcrumbs$: Observable<Breadcrumb[]>;

  constructor(private service: BreadcrumbsService) {
    this.breadcrumbs$ = service.breadcrumbs$;
  }

}
