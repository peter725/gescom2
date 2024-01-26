import { Component, OnInit } from '@angular/core';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
// import { AuthSubject } from '@base/shared/security';
import { CrudImplService, RequestConfig } from "@libs/crud-api";
// import { ContextService } from '@libs/security';
import { Observable } from 'rxjs';
import {MatTableModule} from "@angular/material/table";
import { Approach } from "@libs/sdk/approach";
import { ExportFileType } from "@base/shared/export-file";
import { ColumnSrc } from "@base/shared/collections";


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

  constructor(
    crudService: CrudImplService<any>,
    filterService: FilterService,
    // private authContext: AuthContextService<AuthSubject>,
  ) {
    super(crudService, filterService);
    // this.subject$ = authContext.get();
  }

  override async ngOnInit() {
    await super.ngOnInit();
    //this.monitorCtxChanges();
  }

  protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();
    //const scope = (await firstValueFrom(this.sampleCtx.scope$)).scopeCode;

    config.queryParams = {
      ...config.queryParams
      //scope,
    };
    return config;
  }

  protected getColumns(): ColumnSrc[] {
    return ['year', 'autonomusCommunity', 'approach', 'type', 'actions'];
  }


}
