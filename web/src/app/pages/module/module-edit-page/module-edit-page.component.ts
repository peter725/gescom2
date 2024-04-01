import { Component } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { Module, ModuleForm } from '@libs/sdk/module';

@Component({
    selector: 'tsw-module-edit-page',
    templateUrl: './module-edit-page.component.html',
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ]
})
export class ModuleEditPageComponent extends EditPageBaseComponent<Module, ModuleForm> {
    readonly resourceName = 'modules';

    protected override _createResourceTitle = 'pages.module.add';
    protected override _editResourceTitle = 'pages.module.edit';

    protected buildForm(): FormGroup<ControlsOf<ModuleForm>> {

        return this.fb.group<ControlsOf<ModuleForm>>({
            id: this.fb.control(0),
            languageId: this.fb.control(1),

            name: this.fb.control(null, [Validators.required, Validators.maxLength(100)]),
            description: this.fb.control('', [Validators.maxLength(500)]),
            code: this.fb.control('', [Validators.required] ),
            type: this.fb.control(null, [Validators.required]),
        });
    }
}
