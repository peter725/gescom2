import {Component} from '@angular/core';
import {FORM_STATUS} from '@base/shared/components/form';
import {EditPageBaseComponent} from '@base/shared/pages/edit-page-base.component';
import {ComponentStatus} from '@libs/commons';
import {ControlsOf} from '@libs/commons/form';
import {Arbitration, ArbitrationForm} from "@libs/sdk/arbitration";


@Component({
    selector: 'tsw-arb-edit-step1',
    templateUrl: './arbitration-step1.component.html',
    styleUrls: ['./arbitration-step1.component.scss'],
    providers: [
        {provide: FORM_STATUS, useValue: new ComponentStatus('IDLE')}
    ]
})
export class ArbitrationStep1Component extends EditPageBaseComponent<Arbitration, ArbitrationForm> {

    readonly resourceName = 'arbitration';

    protected override _createResourceTitle = 'pages.field.add';
    protected override _editResourceTitle = 'pages.field.edit';

    protected override getRedirectAfterSaveRoute(): string[] {
        return ['../documents'];
    }

    protected override async save() {

        if (this.redirectAfterSave) {
            await this.router.navigate(this.redirectAfterSavePath, {relativeTo: this.route});
        }
    }

    protected buildForm() {
        return this.fb.group<ControlsOf<ArbitrationForm>>({
            id: this.fb.control(0),
            claimantType: this.fb.control(1),
            name: this.fb.control(null,),
            socialReason: this.fb.control(null,),
            nif: this.fb.control(null),
            surnames: this.fb.control(null),
            arbitrationType: this.fb.control(null),
            phone: this.fb.control(null),
            email: this.fb.control(null),
            address: this.fb.control(null),
            block: this.fb.control(null),
            door: this.fb.control(null),
            floor: this.fb.control(null),
            ladder: this.fb.control(null),
            number: this.fb.control(null),
            portal: this.fb.control(null),
            roadType: this.fb.control(null),
            postalCode: this.fb.control(null),
            province: this.fb.control(null),
            town: this.fb.control(null),

            claimedName: this.fb.control(null),
            claimedNif: this.fb.control(null),
            claimedEmail: this.fb.control(null),
            claimedPhone: this.fb.control(null),
            claimedProvince: this.fb.control(null),
            notificationType: this.fb.control(null),
            claimContent: this.fb.control(null),
            presentation: this.fb.control(null),
            observation: this.fb.control(null)
        });
    }

}
