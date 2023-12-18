import { Component, Input } from '@angular/core';
import { DatasetGroupContainerUI, datasetTrackByFn } from '../../sample-dataset';


@Component({
  selector: 'tsw-sample-dataset-group',
  template: `
    <tsw-sample-dataset-row
      *ngFor="let item of (container.visibleItems$ | async); trackBy: trackByFn"
      class="grid lg:grid-flow-col lg:auto-cols-[1fr] gap-1.5 mb-1.5"
      [container]="item"
    ></tsw-sample-dataset-row>`,
})
export class SampleDatasetGroupComponent {
  @Input() container!: DatasetGroupContainerUI;

  readonly trackByFn = datasetTrackByFn;
}
