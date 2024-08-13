import {Component} from '@angular/core';
import {FormGroup, Validators} from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { AppError, ComponentStatus, ControlsOf } from '@libs/commons';
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
      name: this.fb.control(null, [Validators.required, CustomValidators.allowedName, Validators.maxLength(40)]),
      surname: this.fb.control(null, [Validators.required, CustomValidators.allowedName, Validators.maxLength(50)]),
      lastSurname: this.fb.control(null, [CustomValidators.allowedName, Validators.maxLength(50)]),
      dni: this.fb.control(null, [Validators.required, CustomValidators.nif]),
      email: this.fb.control(null, [Validators.required, Validators.email]),
      phone: this.fb.control(null, [Validators.required, Validators.pattern('^[0-9]*$'), Validators.maxLength(10)]),
      role: this.fb.control(null, [Validators.required]),
      modules: this.fb.control([]),
      autonomousCommunity: this.fb.control(null, [Validators.required]),
      userType: this.fb.control(null, [Validators.required]),
    });
  }

  protected override async afterSaveError(e: any) {
    const err = AppError.parse(e);
    this.notification.show({
      title: 'Error al guardar usuario',
      message: err.message
    });

    this.notification.afterClosed().subscribe(() => {
      // Vuelve al formulario y coloca el foco en el campo DNI después de cerrar el diálogo de notificación.
      /*this.form.controls['dni'];*/
    });

    // Resto del manejo de errores
    this.status.status = 'IDLE';
    this.form.markAsUntouched();
  }

}
