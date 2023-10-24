import { Component } from '@angular/core';
import { FilterComponent } from '@base/shared/filter';
import { ControlsOf } from '@libs/commons';
import { UserFilterForm } from '@libs/sdk/user';

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-user-list-page-filter',
  templateUrl: './user-list-page-filter.component.html'
})
export class UserListPageFilterComponent extends FilterComponent<UserFilterForm> {

  readonly resourceName = 'users';

  protected buildQueryForm() {
    return this.fb.group<ControlsOf<UserFilterForm>>({
      name: this.fb.control(null),
      firstSurname: this.fb.control(null),
      secondSurname: this.fb.control(null),
      email: this.fb.control(null),
      nif: this.fb.control(null),
      phone: this.fb.control(null),
      profile: this.fb.control(null),
      state: this.fb.control(null),
    });
  }
}
