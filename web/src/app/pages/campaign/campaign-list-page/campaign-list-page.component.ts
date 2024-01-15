import { Component, OnInit } from '@angular/core';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { Campaign } from '@libs/sdk/campaign';
import { ExportFileType } from '@base/shared/export-file';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { FilterService } from '@base/shared/filter';
import { ColumnSrc } from '@base/shared/collections';

@Component({
  selector: 'tsw-campaign-list-page',
  templateUrl: './campaign-list-page.component.html',
  styleUrls: ['./campaign-list-page.component.scss']
})
export class CampaignListPageComponent  extends BaseListPageComponent<Campaign> implements OnInit{

    readonly resourceName = 'campaign';

    override exportFormats = [ExportFileType.CSV];
    override downloadFileName = 'pages.campaign.title';

    constructor(
      crudService: CrudImplService<Campaign>,
      filterService: FilterService,
      //private sampleCtx: AppContextService,
    ) {
      super(crudService, filterService);
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
      return [
        'year',
        'ambit',
        'campaignType',
        'nameCampaign',
        'phaseCampaign',
        'actions',
      ];
    }

  //private monitorCtxChanges() {
  // this.sampleCtx.scope$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
  //}
}