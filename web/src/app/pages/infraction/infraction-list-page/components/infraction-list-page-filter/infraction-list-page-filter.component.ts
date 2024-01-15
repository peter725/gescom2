import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FilterComponent } from '@base/shared/filter';
import { InfractionFilterForm } from '@libs/sdk/infraction';


@Component({
  selector: 'tsw-infraction-list-page-filter',
  templateUrl: './infraction-list-page-filter.component.html',
})
export class InfractionListPageFilterComponent extends FilterComponent<InfractionFilterForm> {

  readonly resourceName = 'infraction';

  protected buildQueryForm(): FormGroup {

    return this.fb.group({
      code: this.fb.control(null),
      name: this.fb.control(null),
      createdAtGTE: this.fb.control(null),
      createdAtLTE: this.fb.control(null),
      updatedAtGTE: this.fb.control(null),
      updatedAtLTE: this.fb.control(null),
    });
  }
}