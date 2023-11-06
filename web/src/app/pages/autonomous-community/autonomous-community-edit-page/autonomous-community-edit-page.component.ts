import { Component } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import {AutonomousCommunity, CcaaForm} from "@libs/sdk/AutonomousCommunity";

@Component({
    selector: 'tsw-autonomous-community-edit-page',
    templateUrl: './autonomousCommunity-edit-page.component.html',
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ]
})
export class AutonomousCommunityEditPageComponent extends EditPageBaseComponent<AutonomousCommunity, CcaaForm> {

    readonly resourceName = 'ccaa';

    protected override _createResourceTitle = 'pages.ccaa.add';
    protected override _editResourceTitle = 'pages.ccaa.edit';

    protected buildForm(): FormGroup<ControlsOf<CcaaForm>> {
        return this.fb.group<ControlsOf<CcaaForm>>({
            id: this.fb.control(null),
            name: this.fb.control(null, [Validators.required, Validators.maxLength(100)])
        });

    }
}
