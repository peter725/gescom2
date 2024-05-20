import { Component, OnInit } from '@angular/core';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { CrudImplService, RequestConfig } from "@libs/crud-api";
import { Approach } from "@libs/sdk/approach";
import { ExportFileType } from "@base/shared/export-file";
import { ColumnSrc } from "@base/shared/collections";
import { AuthContextService } from '@libs/security';


@Component({
  selector: 'tsw-approach-list-page',
  templateUrl: './approach-list-page.component.html',
  styleUrls: ['./approach-list-page.component.scss'],
})
export class ApproachListPageComponent extends BaseListPageComponent<Approach> implements OnInit {

  readonly resourceName = 'campaignProposal';
  // subject$: Observable<AuthSubject>;

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.user.title';

  canModify = false;
  canDelete = false;
  userAutonomousCommunity: string;
  userRole: string;

  constructor(
    crudService: CrudImplService<any>,
    filterService: FilterService,
    authContext: AuthContextService,
    // private authContext: AuthContextService<AuthSubject>,
  ) {
    super(crudService, filterService);
    this.canModify = authContext.instant().canWrite('approach');
    this.canDelete = authContext.instant().canDelete('approach');
    this.userAutonomousCommunity = authContext.instant().getAutonomousCommunity();
    this.userRole = authContext.instant().getRole();
    // this.subject$ = authContext.get();
  }

  override async ngOnInit() {
    await super.ngOnInit();
    console.log('entro');
    //this.monitorCtxChanges();
  }

  /*protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();
    //const scope = (await firstValueFrom(this.sampleCtx.scope$)).scopeCode;

    config.queryParams = {
      ...config.queryParams
      //scope,
    };
    return config;
  }*/

  protected getColumns(): ColumnSrc[] {
    return ['year', 'type', 'autonomusCommunity', 'approach', 'actions'];
  }


}
