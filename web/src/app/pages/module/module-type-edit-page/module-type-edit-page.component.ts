import { Component } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { ModuleType, ModuleTypeForm } from '@libs/sdk/module-type';


@Component({
    selector: 'tsw-module-type-edit-page',
    templateUrl: './module-type-edit-page.component.html',
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ]
})
export class ModuleTypeEditPageComponent extends EditPageBaseComponent<ModuleType, ModuleTypeForm> {
    readonly resourceName = 'moduleTypes';

    protected override _createResourceTitle = 'pages.moduleType.add';
    protected override _editResourceTitle = 'pages.moduleType.edit';

    protected buildForm(): FormGroup<ControlsOf<ModuleTypeForm>> {
        return this.fb.group<ControlsOf<ModuleTypeForm>>({
            id: this.fb.control(null),
            languageId: this.fb.control(1),
            name: this.fb.control(null, [Validators.required, Validators.maxLength(100)]),
            description: this.fb.control(null, [Validators.maxLength(500)]),
        });
    }

    protected override getRedirectAfterSaveRoute() {
        return this.namedRoutes.getRoute('moduleTypeManagementList');
    }
}
