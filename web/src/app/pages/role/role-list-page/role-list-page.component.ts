import { Component, OnInit } from '@angular/core';
import { ExportFileType } from '@base/shared/export-file';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { AppAuthSubject } from '@base/shared/security';
import { CrudImplService } from '@libs/crud-api';
import { AuthContextService } from '@libs/security';
import { Observable } from 'rxjs';


@Component({
  selector: 'tsw-role-list-page',
  templateUrl: './role-list-page.component.html',
})
export class RoleListPageComponent extends BaseListPageComponent implements OnInit {

  readonly resourceName = 'roles';
  subject$: Observable<AppAuthSubject>;

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.role.title';

  constructor(
    crudService: CrudImplService<any, number>,
    filterService: FilterService,
    private authContext: AuthContextService<AppAuthSubject>,
  ) {
    super(crudService, filterService);
    this.subject$ = authContext.get();
  }

  protected getColumns() {
    return ['name', 'actions'];
  }

}
