import { Component, ElementRef, EventEmitter, Input, OnInit, Output, } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { ColumnSource } from '@base/shared/collections';
import { DownloadFileConfig, FileDownloaderImplService } from '@base/shared/export-file';
import { COMMON_EXPORT_FORMATS } from '@base/shared/export-file/export-file.constant';
import { DownloadFileColumnDef, ExportFileType } from '@base/shared/export-file/export-file.model';
import { btnBuilder, BtnDef, BtnSrc } from '@libs/commons';
import { RequestParams } from '@libs/crud-api';
import { DownloadOptions } from '@libs/file';
import { PaginatorOptions } from '@libs/mat/paginator';
import { firstValueFrom } from 'rxjs';
import { TableSettingsComponent } from '../table-settings/table-settings.component';


// Ver: angular material custom MatColumnDef component
// muy interesante: https://stackoverflow.com/questions/52844999/custom-angular-6-material-table-component-with-custom-columns
// demo: https://stackblitz.com/edit/angular-wm1psg?file=src%2Fapp%2Fapp.component.html,src%2Fapp%2Fcomponents%2Fdyn-table%2Fdyn-column.component.ts,src%2Fapp%2Fcomponents%2Fdyn-table%2Fdyn-table.component.ts
// https://lightrun.com/answers/angular-components-table-add-a-full-featured-but-limited-table-component-and-array-based-data-source
@Component({
  selector: 'tsw-table-container',
  templateUrl: './table-container.component.html',
  styleUrls: ['./table-container.component.scss'],
})
export class TableContainerComponent<T = any> implements OnInit {
  @Input() source!: MatTableDataSource<T>;
  @Input() columns: ColumnSource | undefined;
  @Input() paginator: PaginatorOptions | undefined;

  @Input() showFullScreen = true;
  @Input() showRefresh = true;
  @Input() showSettings = true;

  @Input() loading = true;

  @Input() exportFormats: (string | BtnSrc)[] = [];
  @Input() resourceName = '';
  @Input() downloadFilePrefix = 'GESCOM';
  @Input() downloadFileName = '';
  @Input() queryParams: RequestParams | undefined;

  @Output() reload = new EventEmitter<void>();
  @Output() page = new EventEmitter<PageEvent>();

  settingsRef: MatDialogRef<TableSettingsComponent> | undefined;

  exportButtonText = 'generic.actions.exportAs';
  exportButtonParams: Record<string, string> = {};

  readonly tagName: string;

  private _exportActions: BtnDef[] = [];
  private _tableActions: BtnDef[] = [];

  constructor(
      public elem: ElementRef,
      private dialog: MatDialog,
      private translate: TranslateService,
      private fileDownloader: FileDownloaderImplService
  ) {
    this.tagName = elem.nativeElement.tagName.toLowerCase();
  }


  ngOnInit() {
    this.configureExportActions();
  }

  get exportActions() {
    return this._exportActions;
  }

  @Input() set tableActions(actions: BtnDef[]) {
    this._tableActions = actions;
  }

  get tableActions() {
    return this._tableActions;
  }

  openSettings() {
    if (!this.columns?.length) {
      return;
    }

    if (this.settingsRef) {
      this.settingsRef.close();
      this.clearSettingsRef();
    }

    this.settingsRef = this.dialog.open(TableSettingsComponent, {
      minWidth: '15%',
      data: this.columns,
      closeOnNavigation: true,
    });
    this.settingsRef.afterClosed().subscribe(() => this.clearSettingsRef());
  }

  private clearSettingsRef() {
    setTimeout(() => (this.settingsRef = undefined), 100);
  }

  private configureExportActions() {
    const exportActions: BtnDef[] = [];

    this.exportFormats.forEach(format => {
      let buttonSrc: BtnSrc;

      if (typeof format === 'string') {
        buttonSrc = COMMON_EXPORT_FORMATS[format];
        if (!buttonSrc) {
          return;
        }

        switch (format) {
          case ExportFileType.CSV:
            buttonSrc.handler = () => this.exportToCSV();
            break;
          case ExportFileType.EXCEL:
            buttonSrc.handler = () => this.exportToExcel();
            break;
          case ExportFileType.PDF:
            buttonSrc.handler = () => this.exportToPDF();
            break;
        }
      } else {
        buttonSrc = format;
      }

      exportActions.push(btnBuilder(buttonSrc));
    });

    this.exportButtonText = 'generic.actions.exportAs';
    if (exportActions.length === 1) {
      this.exportButtonText = 'generic.actions.exportAsFormat';
      this.exportButtonParams = {
        format: exportActions[0].text,
      };
    }

    this._exportActions = exportActions;
  }

  private async exportToExcel() {
    console.log('Export Excel');
  }

  private async exportToCSV() {
    const filename = [
      this.downloadFilePrefix,
      this.downloadFileName.toLowerCase(),
      Date.now(),
    ].join('_');
    const options: DownloadOptions = {
      filename: `${ filename }.csv`, // Nombre del archivo CSV
      type: 'text/csv', // Tipo MIME del archivo CSV
    };

    let columns: DownloadFileColumnDef[] = [];
    if (this.columns) {
      columns = this.columns.getReportableColumns().map(v => ({
        name: v.property,
        label: this.translate.instant(v.label),
      }));
    }

    const reportSuffix = 'ReportsCSV';
    const queryParams = this.queryParams;

    const downloadConfig: DownloadFileConfig = {
      method: 'post',
      resourceName: this.resourceName + reportSuffix,
      options,
      columns,
      queryParams: queryParams // filtros de la entidad
    };

    await this.fileDownloader.downloadFile(downloadConfig);
  }

  private async exportToPDF() {
    console.log('Export PDF');
  }

}
