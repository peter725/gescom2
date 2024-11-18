import {FORM_STATUS} from "@base/shared/components/form";
import {ComponentStatus, ControlsOf} from "@libs/commons";
import {EditPageBaseComponent} from "@base/shared/pages/edit-page-base.component";
import {FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "@libs/validators";
import {Component} from "@angular/core";
import {Approach, CreateApproach} from "@libs/sdk/approach";

@Component({
    selector: 'tsw-approach-see-page',
    templateUrl: './approach-see-page.component.html',
    styleUrls: ['./approach-see-page.component.scss'],
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ]
})

export class ApproachSeePageComponent extends EditPageBaseComponent<Approach, CreateApproach> {

    readonly resourceName = "campaignProposal";

    protected override _createResourceTitle = "pages.approach.add";
    protected override _editResourceTitle = "pages.approach.see";
    public override redirectAfterSave = false;

    cancelRedirectPath = '../../consulta';

    protected buildForm(){
        return this.fb.group<ControlsOf<CreateApproach>>({
            id: this.fb.control(null),
            campaignTypeId: this.fb.control(null, [Validators.required]),
            approach: this.fb.control({value: null, disabled: true}),
            justification: this.fb.control({value: null, disabled: true}),
            objective: this.fb.control({value: null, disabled: true}),
            viability: this.fb.control({value: null, disabled: true}),
            autonomousCommunityName: this.fb.control({value: null, disabled: true}),
            campaignTypeName: this.fb.control(null),
            year: this.fb.control({value: null, disabled: true}),
            date: this.fb.control(null),

        });

    }
}