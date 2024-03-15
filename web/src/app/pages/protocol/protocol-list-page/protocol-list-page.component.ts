import { Component, OnInit } from '@angular/core';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { CrudImplService, RequestConfig } from "@libs/crud-api";
import { ExportFileType } from "@base/shared/export-file";
import { ColumnSrc } from "@base/shared/collections";
import { Protocol } from '@libs/sdk/protocol';


@Component({
  selector: 'tsw-protocol-list-page',
  templateUrl: './protocol-list-page.component.html',
  styleUrls: ['./protocol-list-page.component.scss'],
})
export class ProtocolListPageComponent extends BaseListPageComponent<Protocol> implements OnInit {

  readonly resourceName = 'protocol';

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.user.title';

  constructor(
    crudService: CrudImplService<any>,
    filterService: FilterService,
  ) {
    super(crudService, filterService);
  }

  override async ngOnInit() {
    await super.ngOnInit();
    console.log('entro');
  }

  protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();

    config.queryParams = {
      ...config.queryParams
    };
    return config;
  }

  protected getColumns(): ColumnSrc[] {
    return ['code', 'name', 'nameCampaign', 'actions'];
  }


}
