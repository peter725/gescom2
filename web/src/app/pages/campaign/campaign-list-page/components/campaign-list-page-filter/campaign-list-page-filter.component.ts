import { Component } from '@angular/core';
import { FilterComponent } from '@base/shared/filter';
import { CampaignFilterForm } from '@libs/sdk/campaign';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'tsw-campaign-list-page-filter',
  templateUrl: './campaign-list-page-filter.component.html',
})
export class CampaignListPageFilterComponent extends FilterComponent<CampaignFilterForm> {

  readonly resourceName = 'campaign';

  protected buildQueryForm(): FormGroup {

    return this.fb.group({
      year: this.fb.control(null),
      ambit: this.fb.control(null),
      campaignType: this.fb.control(null),
      nameCampaign: this.fb.control(null),
      phaseCampaign: this.fb.control(null),
      createdAtGTE: this.fb.control(null),
      createdAtLTE: this.fb.control(null),
      updatedAtGTE: this.fb.control(null),
      updatedAtLTE: this.fb.control(null),
    });
  }
}