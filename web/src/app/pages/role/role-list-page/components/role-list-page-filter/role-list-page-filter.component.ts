import { Component } from '@angular/core';
import { FilterComponent } from '@base/shared/filter';
import { ControlsOf } from '@libs/commons';
import { UserFilterForm } from '@libs/sdk/user';
import { RoleFilterForm } from '@libs/sdk/role';

@Component({
  selector: 'tsw-role-list-page-filter',
  templateUrl: './role-list-page-filter.component.html'
})
export class RoleListPageFilterComponent extends FilterComponent<RoleFilterForm> {

  readonly resourceName = 'users';

  protected buildQueryForm() {
    return this.fb.group<ControlsOf<RoleFilterForm>>({
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
