import { Component } from '@angular/core';
import {FormGroup, Validators} from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { CustomValidators } from '@libs/validators';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { Campaign, CreateCampaign } from '@libs/sdk/campaign';

@Component({
  selector: 'app-campaign-add-page',
  templateUrl: './campaign-add-page.component.html',
  styleUrls: ['./campaign-add-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' }},
  ]
})
export class CampaignAddPageComponent extends EditPageBaseComponent<Campaign, CreateCampaign> {

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
      specialists: this.fb.control([], [Validators.required]),
      proponents: this.fb.control([], [Validators.required]),
      responsibleEntity: this.fb.control(null, [Validators.required]),
    });
  }
}
