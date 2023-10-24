import { BooleanInput, coerceBooleanProperty } from '@angular/cdk/coercion';
import { Component, HostBinding, Input } from '@angular/core';
import { ColumnSource } from '@base/shared/collections';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-table-empty-row, tr[tsw-table-empty-row]',
  template: `
    <td class="mat-cell py-3" [colSpan]="colSpan">
      <div class="py-2">
        <span *ngIf="loading">{{ 'generic.actions.loading' | translate }}</span>
        <span *ngIf="!loading">{{ 'text.other.emptyList' | translate }}</span>
      </div>
    </td>`,
})
export class TableEmptyRowComponent {
  private _colSpan = 1;
  private _loading = false;

  @HostBinding('class')
  cssClass = 'mat-row';

  @Input()
  set columns(cols: ColumnSource) {
    if (cols) this._colSpan = cols.length;
  }

  get colSpan() {
    return this._colSpan;
  }

  @Input()
  set loading(value: BooleanInput) {
    const next = coerceBooleanProperty(value);
    if (this._loading !== next) this._loading = next;
  }

  get loading() {
    return this._loading;
  }
}
