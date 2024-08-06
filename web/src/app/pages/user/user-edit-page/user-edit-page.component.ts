import {CreateUser, User} from "@libs/sdk/user";
import {FORM_STATUS} from "@base/shared/components/form";
import { AppError, ComponentStatus, ControlsOf } from '@libs/commons';
import {EditPageBaseComponent} from "@base/shared/pages/edit-page-base.component";
import {Validators} from "@angular/forms";
import {CustomValidators} from "@libs/validators";
import {Component} from "@angular/core";

@Component({
    selector: 'tsw-user-edit-page',
    templateUrl: './user-edit-page.component.html',
    styleUrls: ['./user-edit-page.component.scss'],
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ]
})

export class UserEditPageComponent extends EditPageBaseComponent<User, CreateUser> {
    readonly resourceName = 'users';
    protected override _createResourceTitle = 'pages.user.add';
    protected override _editResourceTitle = 'pages.user.edit';


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
            message: 'No se puede crear el usuario porque el NIF ya está registrado en el sistema.',
            icon: 'error_outline', // Mejor icono
            color: 'warn', // Color de la notificación
            type: 'danger', // Tipo de notificación
            details: [] // Puedes agregar detalles adicionales si es necesario
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