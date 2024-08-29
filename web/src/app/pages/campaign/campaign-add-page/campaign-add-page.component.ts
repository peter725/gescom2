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

  override redirectAfterSave = false;

  protected buildForm(){
    return this.fb.group<ControlsOf<CreateCampaign>>({
      id: this.fb.control(null),
      year: this.fb.control(null, [Validators.required, Validators.pattern(/^\d{4}$/)]),
      codeCpa: this.fb.control(null, [Validators.required]),
      nameCampaign: this.fb.control(null, [Validators.required]),
      campaignType: this.fb.control(null, [Validators.required]),
      participants: this.fb.control([], [Validators.required]),
      ambit: this.fb.control(null, [Validators.required]),
      specialists: this.fb.control([]),
      proponents: this.fb.control([], [Validators.required]),
      autonomousCommunityResponsible: this.fb.control(null, [Validators.required]),
      protocols: this.fb.control([]),
    });
  }

  protected async enviarFormulario(){

    if(this.form.valid){
      this.submitForm();
      this.resetForm();
      
    }


  }

  override resetForm() {
    Object.keys(this.form.controls).forEach(controlName => {
      const control = this.form.get(controlName);
      control?.setValue(null); // Establecer el valor del control en null
      control?.markAsPristine(); // Marcar el control como "prístino"
      control?.markAsUntouched(); // Marcar el control como "no tocado"
      control?.setErrors(null); // Limpiar cualquier error en el control
    });
  }



}
