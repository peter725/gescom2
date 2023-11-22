import { Component, OnInit } from '@angular/core';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
// import { AuthSubject } from '@base/shared/security';
import { CrudImplService } from '@libs/crud-api';
// import { ContextService } from '@libs/security';
import { Observable } from 'rxjs';
import {MatTableModule} from "@angular/material/table";


@Component({
  selector: 'tsw-approach-list-page',
  templateUrl: './approach-list-page.component.html',
  styleUrls: ['./approach-list-page.component.scss'],
})
export class ApproachListPageComponent extends BaseListPageComponent implements OnInit {

  readonly resourceName = 'approach';
  // subject$: Observable<AuthSubject>;

  constructor(
    crudService: CrudImplService<any>,
    filterService: FilterService,
    // private authContext: AuthContextService<AuthSubject>,
  ) {
    super(crudService, filterService);
    // this.subject$ = authContext.get();
  }

  protected getColumns() {
    return ['year', 'autonomusCommunity', 'approach', 'type', 'actions'];
  }

  protected readonly console = console;
}
