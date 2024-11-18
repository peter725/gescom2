import {Component} from '@angular/core';
import {FilterComponent} from '@base/shared/filter';
import {FormGroup} from "@angular/forms";
import {ApproachFilterForm} from "@libs/sdk/approach";

@Component({
    selector: 'tsw-approach-list-page-filter',
    templateUrl: './approach-list-page-filter.component.html'
})
export class ApproachListPageFilterComponent extends FilterComponent<ApproachFilterForm> {

    readonly resourceName = 'campaignProposal';

    protected buildQueryForm(): FormGroup {

        return this.fb.group({
            year: this.fb.control(null),
            autonomusCommunity: this.fb.control(null),
            approach: this.fb.control(null),
            type: this.fb.control(null),
            state: this.fb.control(null),
        });
    }

    protected override getQuerySource(): ApproachFilterForm {
        console.log('enviamos', super.getQuerySource())
        return super.getQuerySource();
    }
}
