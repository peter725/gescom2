import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { CatCodeBuilderDialogComponent } from '@tulsa/app/shared/catalogue-browser/components';
import { CatDialogData, CatDialogResult, GeneratedCode } from '@tulsa/app/shared/catalogue-browser/models';
import { DatasetColumnUI } from '../../sample-dataset';
import { SampleDatasetManagerService } from '../../sample-dataset-manager.service';
import { firstValueFrom, Observable, ReplaySubject } from 'rxjs';


const FIELD_CODE_SUFFIX = 'Code';
const FIELD_TEXT_SUFFIX = 'Text';

@Component({
  selector: 'tsw-sample-dataset-col-catalogue',
  templateUrl: './sample-dataset-col-catalogue.component.html'
})
export class SampleDatasetColCatalogueComponent implements OnInit {
  @Input() container!: DatasetColumnUI<GeneratedCode>;

  displayValue = '';

  readonly helpIndicator$: Observable<boolean>;

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    private service: SampleDatasetManagerService,
    private dialog: MatDialog,
  ) {
    this.helpIndicator$ = this.service.showHelpIndicator$;
  }

  ngOnInit(): void {
    this.updateDisplayValue();
  }

  updateDisplayValue() {
    const value = this.container.formCtrl.value as GeneratedCode;
    this.displayValue = value?.description || value?.code || '';
  }

  async searchCatalogue() {
    if (!this.container.catalogueId || !this.container.hierarchyId) return;

    const data: CatDialogData = {
      title: this.container.label,
      descriptions: [this.container.description],
      catalogueId: this.container.catalogueId,
      hierarchyId: this.container.hierarchyId,
    };

    const opts: MatDialogConfig = {
      minWidth: '80vw',
      maxWidth: '90vw',
      data,
    };

    const ref = this.dialog.open<
      CatCodeBuilderDialogComponent,
      CatDialogData,
      CatDialogResult
    >(CatCodeBuilderDialogComponent, opts);
    const result = await firstValueFrom(ref.afterClosed());

    if (result?.applied) {
      this.updateValue(result)
    }
  }

  private updateValue(result: CatDialogResult) {
    this.container.formCtrl.patchValue(result.code || null);

    // const { ctrlName } = this.container;
    // const isPairOfFields = ctrlName.endsWith(FIELD_TEXT_SUFFIX) || ctrlName.endsWith(FIELD_CODE_SUFFIX);
    // if (isPairOfFields) {
    //   const baseName = ctrlName
    //     .replace(FIELD_CODE_SUFFIX, '')
    //     .replace(FIELD_TEXT_SUFFIX, '');
    //   const codeCtrlName = baseName + FIELD_CODE_SUFFIX;
    //   const textCtrlName = baseName + FIELD_TEXT_SUFFIX;
    //   De alguna forma actualizar los campos code y text
    // }

    this.updateDisplayValue();
  }
}
