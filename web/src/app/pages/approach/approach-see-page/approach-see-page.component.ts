import {CreateUser, User} from "@libs/sdk/user";
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

    readonly resourceName = "approach";

    protected override _createResourceTitle = "pages.approach.add";
    protected override _editResourceTitle = "pages.approach.see";
    public override redirectAfterSave = false;

    protected buildForm(){
        return this.fb.group<ControlsOf<CreateApproach>>({
            id: this.fb.control(null),
            campaignTypeId: this.fb.control(null, [Validators.required]),
            approach: this.fb.control(null, [Validators.required]),
            justification: this.fb.control(null, [Validators.required]),
            objective: this.fb.control(null, [Validators.required]),
            viability: this.fb.control(null, [Validators.required]),
            autonomousCommunity: this.fb.control(null),
        });

    }

}