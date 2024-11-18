import { Component } from '@angular/core';
import { FilterComponent } from '@base/shared/filter';
import { ControlsOf } from '@libs/commons';
import { UserFilterForm } from '@libs/sdk/user';

@Component({
  selector: 'tsw-user-list-page-filter',
  templateUrl: './user-list-page-filter.component.html'
})
export class UserListPageFilterComponent extends FilterComponent<UserFilterForm> {

  readonly resourceName = 'users';

  protected buildQueryForm() {
    return this.fb.group<ControlsOf<UserFilterForm>>({
      name: this.fb.control(null),
      surname: this.fb.control(null),
      lastSurname: this.fb.control(null),
      email: this.fb.control(null),
      dni: this.fb.control(null),
      phone: this.fb.control(null),
      profile: this.fb.control(null),
      state: this.fb.control(null),
    });
  }
}
