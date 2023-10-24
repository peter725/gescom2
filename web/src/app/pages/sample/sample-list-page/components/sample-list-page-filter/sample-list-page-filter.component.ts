import { Component } from '@angular/core';
import { FilterComponent } from '@base/shared/filter';
import { ControlsOf } from '@libs/commons';
import { SampleFilterForm, SampleState } from '@libs/sdk/_old_sample';

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-sample-list-page-filter',
  templateUrl: './sample-list-page-filter.component.html',
  styleUrls: ['./sample-list-page-filter.component.scss'],
})
export class SampleListPageFilterComponent extends FilterComponent<SampleFilterForm> {
  readonly resourceName = 'samples';
  readonly sampleStateSearchParams = { showHidden: false };

  protected buildQueryForm() {
    return this.fb.group<ControlsOf<SampleFilterForm>>({
      pubCode: this.fb.control(null),
      state: this.fb.control(null),
      module: this.fb.control(null),
      sampleStateId: this.fb.control(null),
      seasonName: this.fb.control(null),
      createdAtGTE: this.fb.control(null),
      createdAtLTE: this.fb.control(null),
      updatedAtGTE: this.fb.control(null),
      updatedAtLTE: this.fb.control(null),
    });
  }

  protected override getQuerySource(): SampleFilterForm {
    const obj = { ...this.form.getRawValue() };
    if (obj.sampleStateId) {
      obj.sampleStateId = (obj.sampleStateId as unknown as SampleState).id;
    }
    return { ...obj } as SampleFilterForm;
  }
}
