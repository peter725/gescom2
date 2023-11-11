import {Component} from '@angular/core';
import { Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { CreateUser, User } from '@libs/sdk/user';
import { CustomValidators } from '@libs/validators';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-user-add-page',
  templateUrl: './user-add-page.component.html',
  styleUrls: ['./user-add-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
  ]
})
export class UserAddPageComponent extends EditPageBaseComponent<User, CreateUser> {
  readonly resourceName = 'users';
  protected override _createResourceTitle = 'pages.user.add';

  protected buildForm() {
    return this.fb.group<ControlsOf<CreateUser>>({
      id: this.fb.control(null),
      name: this.fb.control(null, ),
      firstSurname: this.fb.control(null, [Validators.required]),
      secondSurname: this.fb.control(null, []),
      nif: this.fb.control(null, [Validators.required, CustomValidators.nif]),
      emails: this.fb.control([], [Validators.required, Validators.email]),
      phones: this.fb.control([], [Validators.required, Validators.minLength(9)]),
      profile: this.fb.control(null, [Validators.required]),
      position: this.fb.control(null, [Validators.required]),
      areaResponsability: this.fb.control(null, [Validators.required]),
      autonomousCommunity: this.fb.control(null, [Validators.required]),
      authority: this.fb.control(null, [Validators.required]),
    });
  }


}
