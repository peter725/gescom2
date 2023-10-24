import { Component, OnInit } from '@angular/core';
import { ColumnSrc } from '@base/shared/collections';
import { ExportFileType } from '@base/shared/export-file/export-file.model';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { AppContextService } from '@base/shared/app-context';
import { CrudImplService, RequestConfig, RequestParams } from '@libs/crud-api';
import { User } from '@libs/sdk/user';
//import { firstValueFrom, Observable } from 'rxjs';
import { takeUntil, skip } from 'rxjs/operators';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-user-list-page',
  templateUrl: './user-list-page.component.html',
  styleUrls: ['./user-list-page.component.scss'],
})
export class UserListPageComponent extends BaseListPageComponent<User> implements OnInit {

  readonly resourceName = 'users';

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.user.title';

  constructor(
      crudService: CrudImplService<User>,
      filterService: FilterService,
      private sampleCtx: AppContextService,
  ) {
    super(crudService, filterService);
  }

  override async ngOnInit() {
    await super.ngOnInit();
    // this.monitorCtxChanges();
  }

  protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();
    //const scope = (await firstValueFrom(this.sampleCtx.scope$)).scopeCode;

    config.queryParams = {
      ...config.queryParams,
      //scope,
    };
    return config;
  }

  protected getColumns(): ColumnSrc[] {
    return [
      'select',
      'name',
      {
        name: 'surnames',
        compositionProps: ['firstSurname', 'secondSurname'],
      },
      { name: 'firstSurname', visible: false },
      { name: 'secondSurname', visible: false },
      'phone',
      'email',
      //{ name: 'profile', isReportable: false },
      'actions'
    ];
  }

  private monitorCtxChanges() {
    this.sampleCtx.scope$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
  }

}
