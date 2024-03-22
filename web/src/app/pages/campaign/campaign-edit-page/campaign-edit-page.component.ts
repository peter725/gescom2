import { Component } from '@angular/core';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { Campaign, CreateCampaign } from '@libs/sdk/campaign';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';

@Component({
  selector: 'app-campaign-edit-page',
  templateUrl: './campaign-edit-page.component.html',
  styleUrls: ['./campaign-edit-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
  ]
})

export class CampaignEditPageComponent extends EditPageBaseComponent<Campaign, CreateCampaign>{

  readonly resourceName = 'campaign';
  protected override _createResourceTitle = 'pages.campaign.add';
  protected override _editResourceTitle = 'pages.campaign.edit';

  protected buildForm(){
    return this.fb.group<ControlsOf<CreateCampaign>>({
      id: this.fb.control(null),
      year: this.fb.control(null, [Validators.required]),
      codeCpa: this.fb.control(null, [Validators.required]),
      nameCampaign: this.fb.control(null, []),
      campaignType: this.fb.control(null, [Validators.required]),
      participants: this.fb.control([], [Validators.required]),
      ambit: this.fb.control(null, [Validators.required]),
      specialists: this.fb.control([]),
      proponents: this.fb.control([], [Validators.required]),
      autonomousCommunityResponsible: this.fb.control(null, [Validators.required]),
      protocols: this.fb.control([]),
    });
  }
}
