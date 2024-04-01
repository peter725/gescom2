import { Component, OnInit } from '@angular/core';
import { ExportFileType } from '@base/shared/export-file';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { AppAuthSubject } from '@base/shared/security';
import { CrudImplService } from '@libs/crud-api';
import { AuthContextService } from '@libs/security';
import { Observable } from 'rxjs';


@Component({
  selector: 'tsw-profile-list-page',
  templateUrl: './profile-list-page.component.html',
})
export class ProfileListPageComponent extends BaseListPageComponent implements OnInit {

  readonly resourceName = 'profiles';
  subject$: Observable<AppAuthSubject>;

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.profile.title';

  constructor(
    crudService: CrudImplService<any, number>,
    filterService: FilterService,
    private authContext: AuthContextService<AppAuthSubject>,
  ) {
    super(crudService, filterService);
    this.subject$ = authContext.get();
  }

  protected getColumns() {
    return ['select', 'name', 'actions'];
  }

}
