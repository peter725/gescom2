import { Component, ElementRef, EventEmitter, Input, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ColumnSource } from '@base/shared/collections';
import { PaginatorOptions } from '@libs/mat/paginator';
import { TableSettingsComponent } from '@base/shared/components/table/components';


interface ButtonDef {
  title: string;
  icon?: string;
  compact?: boolean;
  action: () => void;
}
// Ver: angular material custom MatColumnDef component
// muy interesante: https://stackoverflow.com/questions/52844999/custom-angular-6-material-table-component-with-custom-columns
// demo: https://stackblitz.com/edit/angular-wm1psg?file=src%2Fapp%2Fapp.component.html,src%2Fapp%2Fcomponents%2Fdyn-table%2Fdyn-column.component.ts,src%2Fapp%2Fcomponents%2Fdyn-table%2Fdyn-table.component.ts
// https://lightrun.com/answers/angular-components-table-add-a-full-featured-but-limited-table-component-and-array-based-data-source

@Component({
  selector: 'tsw-table-container',
  templateUrl: './table-container.component.html',
  styleUrls: ['./table-container.component.scss'],
})
export class TableContainerComponent<T = any> {

  @Input() source!: MatTableDataSource<T>;
  @Input() columns: ColumnSource | undefined;
  @Input() paginator: PaginatorOptions | undefined;

  @Input() showFullScreen = true;
  @Input() showRefresh = true;
  @Input() showSettings = true;

  @Input() loading = true;

  @Output() reload = new EventEmitter<void>();
  @Output() page = new EventEmitter<PageEvent>();

  settingsRef: MatDialogRef<TableSettingsComponent> | undefined;

  readonly tagName: string;

  private _exportActions: ButtonDef[] = [
    { title: 'Excel', icon: '', action: () => { } },
    { title: 'PDF', icon: '', action: () => { } },
    { title: 'XML', icon: '', action: () => { } },
  ];
  private _tableActions: ButtonDef[] = [];

  constructor(
    private dialog: MatDialog,
    public elem: ElementRef
  ) {
    this.tagName = elem.nativeElement.tagName.toLowerCase();
  }

  // this should only be allowed formats!!
  @Input() set exportActions(actions: ButtonDef[]) {
    this._exportActions = actions;
  }

  @Input() set tableActions(actions: ButtonDef[]) {
    this._tableActions = actions;
  }

  get exportActions() {
    return this._exportActions;
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
    setTimeout(() => this.settingsRef = undefined, 100);
  }

  /*ngAfterViewInit(): void {
    this.updateTableWidth();
  }

  private updateTableWidth() {
    setTimeout(() => {
      const elm = this.tableContainer?.nativeElement;
      if (elm) {
        this.tableWidth = `${ elm.clientWidth - 20 }px`;
        console.info(elm);
        console.info(`clientWidth: ${ elm.clientWidth }, offsetWidth: ${ elm.offsetWidth }, scrollWidth: ${ elm.scrollWidth }`);
        this.renderTable = true;
      }
    }, 500);
  }*/
}
