import { Component, OnInit } from '@angular/core';
import { ColumnSrc } from '@base/shared/collections';
import { ExportFileType } from '@base/shared/export-file/export-file.model';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { User } from '@libs/sdk/user';
import { AuthContextService } from '@libs/security';

@Component({
  selector: 'tsw-user-list-page',
  templateUrl: './user-list-page.component.html',
  styleUrls: ['./user-list-page.component.scss'],
})
export class UserListPageComponent extends BaseListPageComponent<User> implements OnInit {

  readonly resourceName = 'users';

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.user.title';

  canModify = false;
  canDelete = false;

  constructor(
      crudService: CrudImplService<User>,
      filterService: FilterService,
      authContext: AuthContextService
      //private sampleCtx: AppContextService,
  ) {
    super(crudService, filterService);
    this.canModify = authContext.instant().canWrite('user');
    this.canDelete = authContext.instant().canDelete('user');
  }

  override async ngOnInit() {
    await super.ngOnInit();
    //this.monitorCtxChanges();
  }

  protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();
    //const scope = (await firstValueFrom(this.sampleCtx.scope$)).scopeCode;

    config.queryParams = {
      ...config.queryParams,
      //scope,
      page: 0 // Aquí estamos forzando el page a 0
    };
    return config;
  }

  protected getColumns(): ColumnSrc[] {
    return [
      'name',
      {
        name: 'surnames',
        compositionProps: ['surname', 'lastSurname'],
      },
      { name: 'firstSurname', visible: false },
      { name: 'secondSurname', visible: false },
      'phone',
      'email',
      { name: 'role', isReportable: false },
      'actions'
    ];
  }

  //private monitorCtxChanges() {
   // this.sampleCtx.scope$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
  //}

}
