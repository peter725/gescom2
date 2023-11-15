import { Component, OnInit } from '@angular/core';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
// import { TulsaAuthSubject } from '@base/shared/security';
import { CrudImplService } from '@libs/crud-api';
// import { AuthContextService } from '@tulsa/libs/security';
import { Observable } from 'rxjs';


@Component({
  selector: 'tsw-arbitration-list-page',
  templateUrl: './approach-list-page.component.html',
  styleUrls: ['./approach-list-page.component.scss'],
})
export class ApproachListPageComponent extends BaseListPageComponent implements OnInit {

  readonly resourceName = 'approach';
  // subject$: Observable<TulsaAuthSubject>;

  constructor(
    crudService: CrudImplService<any>,
    filterService: FilterService,
    // private authContext: AuthContextService<TulsaAuthSubject>,
  ) {
    super(crudService, filterService);
    // this.subject$ = authContext.get();
  }

  protected getColumns() {
    return ['year', 'autonomusCommunity', 'approach', 'type'];
  }

}
