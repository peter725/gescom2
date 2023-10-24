import { Component, Input } from '@angular/core';
import { DatasetRowContainerUI, datasetTrackByFn } from '../../sample-dataset';
import { FieldElementType } from '@tulsa/libs/sdk/field-module';


@Component({
  selector: 'tsw-sample-dataset-row',
  templateUrl: './sample-dataset-row.component.html'
})
export class SampleDatasetRowComponent {
  @Input() container!: DatasetRowContainerUI;
  readonly trackByFn = datasetTrackByFn;
  readonly FieldElementType = FieldElementType;
}
