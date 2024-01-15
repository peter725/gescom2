import { Component, OnInit } from '@angular/core';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { ExportFileType } from '@base/shared/export-file';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { FilterService } from '@base/shared/filter';
import { ColumnSrc } from '@base/shared/collections';
import { Infraction } from '@libs/sdk/infraction';

@Component({
  selector: 'tsw-infraction-list-page',
  templateUrl: './infraction-list-page.component.html',
  styleUrls: ['./infraction-list-page.component.scss'],
})
export class InfractionListPageComponent extends BaseListPageComponent<Infraction> implements OnInit {
  readonly resourceName = 'infraction';

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.infraction.title';
  constructor(
    crudService: CrudImplService<Infraction>,
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
      'select',
      'code',
      'name',
      'actions',
    ];
  }

  //private monitorCtxChanges() {
  // this.sampleCtx.scope$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
  //}
}