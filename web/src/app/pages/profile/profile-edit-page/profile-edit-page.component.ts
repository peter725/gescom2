import { Component } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { Profile, ProfileForm } from '@libs/sdk/profile';

@Component({
  selector: 'tsw-profile-edit-page',
  templateUrl: './profile-edit-page.component.html',
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
  ]
})
export class ProfileEditPageComponent extends EditPageBaseComponent<Profile, ProfileForm> {

  readonly resourceName = 'profiles';

  protected override _createResourceTitle = 'pages.profile.add';
  protected override _editResourceTitle = 'pages.profile.edit';

  protected buildForm(): FormGroup<ControlsOf<ProfileForm>> {
    return this.fb.group<ControlsOf<ProfileForm>>({
      id: this.fb.control(null),
      name: this.fb.control(null, [Validators.required, Validators.maxLength(100)]),
      permissions: this.fb.control([], [Validators.required, Validators.min(1)]),
    });

  }
}
