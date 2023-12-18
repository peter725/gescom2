import {Component} from '@angular/core';
import {FormGroup, Validators} from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { CreateUser, User } from '@libs/sdk/user';
import { CustomValidators } from '@libs/validators';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";


@Component({
  selector: 'tsw-user-add-page',
  templateUrl: './user-add-page.component.html',
  styleUrls: ['./user-add-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' }},
  ]
})
export class UserAddPageComponent extends EditPageBaseComponent<User, CreateUser> {
  readonly resourceName = 'users';
  protected override _createResourceTitle = 'pages.user.add';

  protected buildForm() {
    return this.fb.group<ControlsOf<CreateUser>>({
      id: this.fb.control(null),
      name: this.fb.control(null, [Validators.required, CustomValidators.allowedName]),
      firstSurname: this.fb.control(null, [Validators.required, CustomValidators.allowedName]),
      secondSurname: this.fb.control(null, [CustomValidators.allowedName]),
      nif: this.fb.control(null, [Validators.required, CustomValidators.nif]),
      email: this.fb.control(null, [Validators.required, Validators.email]),
      phone: this.fb.control(null, [Validators.required]),
      profile: this.fb.control(null, [Validators.required]),
      modules: this.fb.control([], [Validators.required, Validators.min(1)]),
      autonomousCommunity: this.fb.control(null, [Validators.required]),
      userType: this.fb.control(null, [Validators.required]),
      password: this.fb.control(null, [Validators.required]),
    });
  }


}
