import { Component, Input, OnDestroy, OnInit, TrackByFunction } from '@angular/core';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { BehaviorSubject, debounceTime, filter, Observable, ReplaySubject, takeUntil } from 'rxjs';
import { DatasetColumnUI, datasetTrackByFn } from '../../sample-dataset';
import { SampleDatasetManagerService } from '../../sample-dataset-manager.service';


@Component({
  selector: 'tsw-sample-dataset-col-combo-multi',
  templateUrl: './sample-dataset-col-combo-multi.component.html',
  styles: [],
})
export class SampleDatasetColComboMultiComponent implements OnInit, OnDestroy {
  @Input() container!: DatasetColumnUI<TulsaFieldModuleTerm[]>;
  readonly helpIndicator$: Observable<boolean>;

  private readonly _comboValues = new BehaviorSubject<TulsaFieldModuleTerm[]>([]);
  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  readonly trackComboBy: TrackByFunction<TulsaFieldModuleTerm> = (_, item) => item.code;

  constructor(private service: SampleDatasetManagerService) {
    this.helpIndicator$ = this.service.showHelpIndicator$;
  }

  get comboValues() {
    return this._comboValues.asObservable();
  }

  ngOnInit(): void {
    // this.monitorSearch();
    // this.monitorComboListSrc();
    // this.filterComboList();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  resetValue() {
    this.container.formCtrl.reset(null);
  }

}
