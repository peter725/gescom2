import {CreateUser, User} from "@libs/sdk/user";
import {FORM_STATUS} from "@base/shared/components/form";
import {ComponentStatus, ControlsOf} from "@libs/commons";
import {EditPageBaseComponent} from "@base/shared/pages/edit-page-base.component";
import {Validators} from "@angular/forms";
import {CustomValidators} from "@libs/validators";
import {Component} from "@angular/core";

@Component({
    // eslint-disable-next-line @angular-eslint/component-selector
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
            name: this.fb.control(null, [Validators.required, CustomValidators.allowedName]),
            firstSurname: this.fb.control(null, [Validators.required, CustomValidators.allowedName]),
            secondSurname: this.fb.control(null, [CustomValidators.allowedName]),
            nif: this.fb.control(null, [Validators.required, CustomValidators.nif]),
            email: this.fb.control(null, [Validators.required, Validators.email]),
            phone: this.fb.control(null, [Validators.required]),
            profile: this.fb.control(null, [Validators.required]),
            modules: this.fb.control([], [Validators.required, Validators.min(1)]),
            autonomousCommunity: this.fb.control(null, [Validators.required]),
        });
    }


}