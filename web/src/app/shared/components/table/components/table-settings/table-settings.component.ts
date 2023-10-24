import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, Inject, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { ColumnSource } from '@base/shared/collections';

export type TempCol = { name: string, label: string, visible: boolean };
export const LABEL_TRANSLATION_PREFIX = 'fields.';

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-table-settings',
  templateUrl: './table-settings.component.html',
  styleUrls: ['./table-settings.component.scss'],
})
export class TableSettingsComponent implements OnInit {
  configurations: TempCol[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ColumnSource,
    private translate: TranslateService
  ) {
  }

  ngOnInit(): void {
    this.generateData();
  }

  private generateData() {
    if (!this.data?.length) {
      this.configurations = [];
      return;
    }

    const excludedCodes = [''];
    this.configurations = this.data.getColumnDefs()
      .filter(v => !excludedCodes.includes(v.name))
      .map(({ name, label, visible, order, }) => ({
        name,
        label: this.translateText(label),
        visible,
        order
      })
    );
  }

  drop(ev: CdkDragDrop<TempCol>) {
    moveItemInArray(this.configurations, ev.previousIndex, ev.currentIndex);
  }

  checkboxChange(ev: MatCheckboxChange, index: number) {
    const temp = this.configurations[index];
    temp.visible = ev.checked;
  }

  close() {
    const order = this.configurations.map(v => v.name);
    const visible = this.configurations.filter(v => v.visible).map(v => v.name);
    this.data.reorder(order);
    this.data.updateVisible(visible);
  }

  private translateText(text: string): string {
    const src = LABEL_TRANSLATION_PREFIX + text;
    const translation = this.translate.instant(src);
    return translation !== src ? translation : text;
  }
}
