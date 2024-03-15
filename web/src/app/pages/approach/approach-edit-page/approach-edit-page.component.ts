
import {FORM_STATUS} from "@base/shared/components/form";
import {ComponentStatus, ControlsOf} from "@libs/commons";
import {EditPageBaseComponent} from "@base/shared/pages/edit-page-base.component";
import { FormGroup, Validators } from '@angular/forms';
import {Component} from "@angular/core";
import { Approach, CreateApproach } from '@libs/sdk/approach';

@Component({
    selector: 'tsw-approach-edit-page',
    templateUrl: './approach-edit-page.component.html',
    styleUrls: ['./approach-edit-page.component.scss'],
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ]
})

export class ApproachEditPageComponent extends EditPageBaseComponent<Approach, CreateApproach> {
    readonly resourceName = 'campaignProposal';
    protected override _createResourceTitle = 'pages.approach.add';
    protected override _editResourceTitle = 'pages.approach.edit';


    protected buildForm(): FormGroup<ControlsOf<CreateApproach>> {
        return this.fb.group<ControlsOf<CreateApproach>>({
            id: this.fb.control(null),
            campaignTypeId: this.fb.control(null, [Validators.required]),
            approach: this.fb.control(null, [Validators.required]),
            justification: this.fb.control(null, [Validators.required]),
            objective: this.fb.control(null, [Validators.required]),
            viability: this.fb.control(null, [Validators.required]),
            autonomousCommunityName: this.fb.control(null),
            campaignTypeName: this.fb.control(null),
            year: this.fb.control(null),
            date: this.fb.control(null),
        });

    }

    protected override afterLoadDataSuccess(startValue: CreateApproach) {
        super.afterLoadDataSuccess(startValue); // Asegúrate de llamar a la implementación de la clase base

        // Asumiendo que `startValue` ya tiene el valor de `campaignTypeId` que deseas establecer
        if (this.form && startValue.campaignTypeId) {
            this.form.get('campaignTypeId')?.setValue(startValue.campaignTypeId, { emitEvent: false });
        }
    }



}