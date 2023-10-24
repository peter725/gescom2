import { Component, Input } from '@angular/core';
import { DatasetColumnUI } from '../../sample-dataset';
import { SampleDatasetManagerService } from '../../sample-dataset-manager.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'tsw-sample-dataset-col-text',
  templateUrl: './sample-dataset-col-text.component.html',
})
export class SampleDatasetColTextComponent {
  @Input() container!: DatasetColumnUI;

  readonly helpIndicator$: Observable<boolean>;

  constructor(private service: SampleDatasetManagerService) {
    this.helpIndicator$ = this.service.showHelpIndicator$;
  }

}
