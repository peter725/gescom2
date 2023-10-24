import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CatalogueBrowserService } from '@tulsa/app/shared/catalogue-browser';
import { TulsaCatalogue, TulsaCatalogueHierarchy } from '@tulsa/libs/sdk/catalogue';
import { firstValueFrom } from 'rxjs';
import { CatDialogData, CatDialogResult } from '../../models';


@Component({
  selector: 'tsw-cat-code-builder-dialog',
  templateUrl: './cat-code-builder-dialog.component.html',
  styleUrls: ['./cat-code-builder-dialog.component.scss'],
})
export class CatCodeBuilderDialogComponent implements OnInit {

  private readonly serviceConfigTimeout = 150;

  ready = false;

  constructor(
    private ref: MatDialogRef<CatCodeBuilderDialogComponent>,
    private catalogueService: CatalogueBrowserService,
    @Inject(MAT_DIALOG_DATA) public data: CatDialogData,
  ) {
  }

  ngOnInit(): void {
    this.configureService();
  }

  async apply() {
    const code = await firstValueFrom(this.catalogueService.generatedCode$);
    this.closeDialog({
      applied: true,
      cancelled: false,
      code,
    });
  }

  reset() {
    this.closeDialog({
      applied: true,
      cancelled: false,
    });
  }

  cancel() {
    this.closeDialog({
      applied: false,
      cancelled: true,
    });
  }

  private configureService() {
    setTimeout(() => {
      const { catalogueId, hierarchyId } = this.data;
      this.catalogueService.useCatalogue({ id: catalogueId } as TulsaCatalogue);
      this.catalogueService.useHierarchy({ id: hierarchyId } as TulsaCatalogueHierarchy);
      this.ready = true;
    }, this.serviceConfigTimeout);
  }

  private closeDialog(result: CatDialogResult) {
    this.ref.close(result);
    this.catalogueService.clearCode();
  }
}
