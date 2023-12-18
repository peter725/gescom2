import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { GeneratedCode } from '@tulsa/app/shared/catalogue-browser/models';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { debounceTime, distinctUntilChanged, ReplaySubject, startWith, takeUntil } from 'rxjs';
import { DatasetGroupContainerUI } from '../../sample-dataset';


@Component({
  selector: 'tsw-sample-dataset-collection-header',
  templateUrl: './sample-dataset-collection-header.component.html',
  styleUrls: ['./sample-dataset-collection-header.component.scss'],
})
export class SampleDatasetCollectionHeaderComponent implements OnInit, OnDestroy {
  @Input() container!: DatasetGroupContainerUI;

  resId: string | undefined;
  paramType: string | undefined;
  paramDescription: string | undefined;
  evalCode: string | undefined;

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  ngOnInit() {
    this.monitorValueChanges();
    // obtener las columnas y los datos necesarios que se pueden mostrar aquí
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  private updateDisplayedData() {
    const value = this.container.formGroup.value;

    if (value['resId']) this.resId = value['resId'] as string;

    if (value['evalCode']) this.evalCode = (value['evalCode'] as TulsaFieldModuleTerm).value;

    if (value['paramType']) this.paramType = (value['paramType'] as TulsaFieldModuleTerm).value;

    if (value['paramText']) this.paramDescription = (value['paramText'] as GeneratedCode).description;

  }

  private monitorValueChanges() {
    this.container.formGroup.valueChanges.pipe(
      startWith(null),
      takeUntil(this.destroyed$),
      debounceTime(100),
      distinctUntilChanged(),
    ).subscribe(() => this.updateDisplayedData());
  }
}
