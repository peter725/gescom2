import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { ExportFileType } from '@base/shared/export-file';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { FilterService } from '@base/shared/filter';
import { ColumnSrc } from '@base/shared/collections';
import { Infringement } from '@libs/sdk/infringement';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { ProductService } from '@libs/sdk/productService';

@Component({
  selector: 'products-list',
  templateUrl: './products-list.component.html'
})
export class ProductsListComponent extends BaseListPageComponent<ProductService> implements OnInit {

  @Output() selectionChanged: EventEmitter<{row: any, selected: boolean}> = new EventEmitter();

  readonly resourceName = 'productServices';

  override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.infringement.title';
  constructor(
    crudService: CrudImplService<ProductService>,
    filterService: FilterService,
    //private sampleCtx: AppContextService,
  ) {
    super(crudService, filterService);
  }

  override async ngOnInit() {
    await super.ngOnInit();
    //this.monitorCtxChanges();
  }

  override select(ev: MatCheckboxChange, row: any): void {
    if (ev) {
      this.selection.toggle(row);
      // Emite el evento con el objeto de la fila y el estado de selección
      this.selectionChanged.emit({row: row, selected: this.selection.isSelected(row)});
    }
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
    ];
  }

  //private monitorCtxChanges() {
  // this.sampleCtx.scope$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
  //}
}