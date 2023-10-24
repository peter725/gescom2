import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { AppContextService } from '@base/shared/app-context';
import { ExportFileType, FileDownloaderImplService } from '@base/shared/export-file';
import { FilterService } from '@base/shared/filter';
import { NotificationService } from '@base/shared/notification';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { GAuthSubject } from '@base/shared/security';
import { BtnSrc } from '@libs/commons';
import {
  CRUD_OPERATIONS,
  CrudImplService,
  CrudOperationStorage,
  RequestConfig,
  RequestParams
} from '@libs/crud-api';
import { B64EncodedFile } from '@libs/file';
import { VSample } from '@libs/sdk/sample';
import { AuthContextService } from '@libs/security';
import { firstValueFrom, Observable } from 'rxjs';
import { skip, takeUntil } from 'rxjs/operators';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-sample-list-page',
  templateUrl: './sample-list-page.component.html',
  styleUrls: [],
})
export class SampleListPageComponent extends BaseListPageComponent<VSample> implements OnInit {
  readonly resourceName = 'samples';
  fileUpload = new FormControl<B64EncodedFile | null>(null);
  subject$: Observable<GAuthSubject>;

  override exportFormats: (string | BtnSrc)[] = [
    { text: 'text.other.sample.download', handler: () => this.downloadSamplesFile() },
    ExportFileType.CSV,
  ];
  override downloadFileName = 'pages.entity.title';

  constructor(
    crudService: CrudImplService<VSample>,
    filterService: FilterService,
    private http: HttpClient,
    @Inject(CRUD_OPERATIONS) private operations: CrudOperationStorage,
    private authContext: AuthContextService<GAuthSubject>,
    private sampleCtx: AppContextService,
    protected notification: NotificationService,
    //private fileDownloader: FileDownloaderImplService,
  ) {
    super(crudService, filterService);
    this.subject$ = this.authContext.get();
  }

  override async ngOnInit() {
    await super.ngOnInit();
    this.monitorCtxChanges();
  }

  protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();
    const scope = (await firstValueFrom(this.sampleCtx.scope$)).scopeCode;
    const moduleId = (await firstValueFrom(this.sampleCtx.module$)).id;
    config.queryParams = {
      ...config.queryParams,
      scope,
      moduleId,
    };
    return config;
  }

  protected getColumns(): string[] {
    return ['select', 'pubCode', 'module', 'season', 'sampleState', 'createdAt', 'updatedAt', 'actions'];
  }

  private monitorCtxChanges() {
    this.sampleCtx.scope$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
    this.sampleCtx.module$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
  }

  protected override createSortSource(): Sort {
    return {
      active: 'createdAt',
      direction: 'desc',
    };
  }

  protected async downloadSamplesFile() {
    const module = await firstValueFrom(this.sampleCtx.module$);
    const season = await firstValueFrom(this.sampleCtx.season$);
    const scope = await firstValueFrom(this.sampleCtx.scope$);

    let queryParams: RequestParams = {};
    if (this.filter.query) {
      queryParams = this.filter.query.toObject();
    }
    queryParams = {
      ...queryParams,
      moduleId: module.id,
      seasonId: season.id,
      scope: scope.scopeCode,
    };

    /*await this.fileDownloader.downloadFile({
      resourceName: 'sampleFileDownload',
      queryParams,
      options: {
        filename: `samples_${ module.code }_${ Date.now() }`,
        type: 'text/xml',
      }
    });*/

    this.notification.show({
      type: 'info',
      message: 'text.other.sample.downloadSuccess',
    }, { ttl: 5 });
  }
}
