import {Component} from "@angular/core";
import {Approach, ApproachForm, CreateApproach} from "@libs/sdk/approach";
import {ComponentStatus, ControlsOf} from "@libs/commons";
import {FormGroup, Validators} from "@angular/forms";
import {EditPageBaseComponent} from "@base/shared/pages/edit-page-base.component";
import {FORM_STATUS} from "@base/shared/components/form";

@Component({
    selector: "tsw-approach-campaign-proposal",
    templateUrl: "./approach-campaign-proposal.component.html",
    styleUrls: ["./approach-campaign-proposal.component.scss"],
    providers: [
        {provide: FORM_STATUS, useValue: new ComponentStatus("IDLE")}
    ]
})

export class ApproachCampaignProposalComponent extends EditPageBaseComponent<Approach, CreateApproach> {

    readonly resourceName = "approach";

    protected override _createResourceTitle = "pages.approach.add";
    protected override _editResourceTitle = "pages.approach.edit";
    public override redirectAfterSave = false;

    protected override getRedirectAfterSaveRoute(): string[] {
        return [`../${this.srcData?.id}/documents`];
    }

    protected override async afterSaveSuccess(result: Approach): Promise<void> {
        await super.afterSaveSuccess(result);
        await this.router.navigate(this.getRedirectAfterSaveRoute(), {relativeTo: this.route});
    }


    protected buildForm(): FormGroup<ControlsOf<CreateApproach>> {
        return this.fb.group<ControlsOf<CreateApproach>>({
            id: this.fb.control(null),
            type: this.fb.control(null, [Validators.required]),
            approach: this.fb.control(null, [Validators.required]),
            justification: this.fb.control(null, [Validators.required]),
            objective: this.fb.control(null, [Validators.required]),
            viability: this.fb.control(null, [Validators.required]),
            autonomousCommunity: this.fb.control(null, [Validators.required]),
            year: this.fb.control(null, [Validators.required]),
        });
    }
}