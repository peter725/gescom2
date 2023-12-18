import { Component } from '@angular/core';
import {FormGroup, Validators} from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { CreateUser, User } from '@libs/sdk/user';
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

  // Definir las propiedades selectedOptions y availableOptions
  availableOptions: string[] = ['Opción 1', 'Opción 2', 'Opción 3']; // Definir tus opciones disponibles aquí
  selectedOptions: string[] = []; // Inicializar el array de opciones seleccionadas

  // Definir las propiedades selectedFromList y selectedToList
  selectedFromList: string[] = [];
  selectedToList: string[] = [];
  //protected override _createResourceTitle = 'pages.campaign.add';

  protected buildForm(){
    return this.fb.group<ControlsOf<CreateCampaign>>({
      id: this.fb.control(null),
      year: this.fb.control(null, [Validators.required]),
      code_cpa: this.fb.control(null, [Validators.required]),
      campaign: this.fb.control(null, []),
      type: this.fb.control(null, [Validators.required, CustomValidators.nif]),
      scope: this.fb.control(null, [Validators.required, Validators.email]),
      responsable_entity: this.fb.control(null, [Validators.required]),
    });
  }


  

  moveToSelected(availableOptions: HTMLSelectElement, selectedOptions: HTMLSelectElement) {
    for (let i = 0; i < availableOptions.selectedOptions.length; i++) {
      const option = availableOptions.selectedOptions[i];
      selectedOptions.appendChild(option);
    }
  }
  
  moveToAvailable(availableOptions: HTMLSelectElement, selectedOptions: HTMLSelectElement) {
    for (let i = 0; i < selectedOptions.selectedOptions.length; i++) {
      const option = selectedOptions.selectedOptions[i];
      availableOptions.appendChild(option);
    }
  }
  

}
