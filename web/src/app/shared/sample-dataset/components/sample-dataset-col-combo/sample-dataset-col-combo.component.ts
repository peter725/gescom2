import { Component, Input, OnDestroy, OnInit, TrackByFunction } from '@angular/core';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { BehaviorSubject, debounceTime, filter, Observable, ReplaySubject, takeUntil } from 'rxjs';
import { DatasetColumnUI } from '../../sample-dataset';
import { SampleDatasetManagerService } from '../../sample-dataset-manager.service';


@Component({
  selector: 'tsw-sample-dataset-col-combo',
  templateUrl: './sample-dataset-col-combo.component.html',
  styles: [],
})
export class SampleDatasetColComboComponent implements OnInit, OnDestroy {
  @Input() container!: DatasetColumnUI<TulsaFieldModuleTerm>;

  readonly helpIndicator$: Observable<boolean>;

  private readonly _comboValues = new BehaviorSubject<TulsaFieldModuleTerm[]>([]);
  private readonly destroyed$ = new ReplaySubject<boolean>(1);
  readonly trackComboBy: TrackByFunction<TulsaFieldModuleTerm> = (_, item) => item.code;
  readonly displayComboFn = (value: TulsaFieldModuleTerm | undefined) => {
    if (value == null) return '';

    const list = Array.isArray(value) ? [...value] : [value];
    let result = list.slice(0, 2).map(item => item.value).join(', ');
    const MAX_VALUES_SHOWN = 2;
    if (list.length > MAX_VALUES_SHOWN) {
      result += `, +${ list.length - MAX_VALUES_SHOWN }`;
    }

    return result;
  };

  constructor(private service: SampleDatasetManagerService) {
    this.helpIndicator$ = this.service.showHelpIndicator$;
  }

  get comboValues() {
    return this._comboValues.asObservable();
  }

  ngOnInit(): void {
    this.monitorSearch();
    this.monitorComboListSrc();
    this.filterComboList();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  optionSelected(value: TulsaFieldModuleTerm) {
    this.container.formCtrl.patchValue(value);
    this.filterComboList();
  }

  resetValue() {
    this.container.formCtrl.reset(null);
    this.filterComboList();
  }

  private monitorSearch() {
    this.container.formCtrl.valueChanges.pipe(
      takeUntil(this.destroyed$),
      debounceTime(250),
      filter(search => typeof search === 'string'),
    ).subscribe(search => this.filterComboList((search as string).trim()));
  }

  private monitorComboListSrc() {
    this.container.comboValues$.pipe(
      takeUntil(this.destroyed$),
      debounceTime(250),
    ).subscribe(() => this.filterComboList());
  }

  private filterComboList(search = '') {
    const list = this.container.comboValues.filter(
      item => `${ item.code }|${ item.value }`.toLowerCase().includes(search.toLowerCase())
    );
    this._comboValues.next(list);
  }
}
