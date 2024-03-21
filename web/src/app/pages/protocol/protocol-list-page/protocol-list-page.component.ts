import { Component, OnInit, Inject  } from '@angular/core';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { CrudImplService, RequestConfig } from "@libs/crud-api";
import { ExportFileType } from "@base/shared/export-file";
import { ColumnSrc } from "@base/shared/collections";
import { Protocol } from '@libs/sdk/protocol';
import { MatDialogRef, MAT_DIALOG_DATA  } from '@angular/material/dialog';
import { SharedDataService } from '@base/services/sharedDataService';


@Component({
  selector: 'tsw-protocol-list-page',
  templateUrl: './protocol-list-page.component.html',
  styleUrls: ['./protocol-list-page.component.scss'],
})
export class ProtocolListPageComponent extends BaseListPageComponent<Protocol> implements OnInit {

  readonly resourceName = 'protocol';

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.user.title';

  dialogRef: MatDialogRef<ProtocolListPageComponent>;


  constructor(
    crudService: CrudImplService<any>,
    filterService: FilterService,
    dialogRef: MatDialogRef<ProtocolListPageComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, // Inyectar los datos
    private sharedDataService: SharedDataService
    
  ) {
    super(crudService, filterService);
    this.dialogRef = dialogRef; 
  }

  override async ngOnInit() {
    await super.ngOnInit();
    console.log('Datos pasados al diálogo:', this.data);

  }

  protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();

    config.queryParams = {
      ...config.queryParams
    };
    return config;
  }

  protected getColumns(): ColumnSrc[] {
    return ['code', 'name', 'actions'];
  }

  close(): void {
    this.dialogRef.close();
  }


}
