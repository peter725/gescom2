import {Component} from "@angular/core";
import {Approach, CreateApproach} from "@libs/sdk/approach";
import {ComponentStatus, ControlsOf} from "@libs/commons";
import {FormGroup, Validators} from "@angular/forms";
import {EditPageBaseComponent} from "@base/shared/pages/edit-page-base.component";
import {FORM_STATUS} from "@base/shared/components/form";
import { MAT_RADIO_DEFAULT_OPTIONS } from "@angular/material/radio";

@Component({
    selector: "tsw-approach-campaign-proposal",
    templateUrl: "./approach-campaign-proposal.component.html",
    styleUrls: ["./approach-campaign-proposal.component.scss"],
    providers: [
        {provide: FORM_STATUS, useValue: new ComponentStatus("IDLE")},
        { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' }},
    ]
})

export class ApproachCampaignProposalComponent extends EditPageBaseComponent<Approach, CreateApproach> {

    readonly resourceName = "campaignProposal";

    protected override _createResourceTitle = "pages.approach.add";
    protected override _editResourceTitle = "pages.approach.edit";


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

}