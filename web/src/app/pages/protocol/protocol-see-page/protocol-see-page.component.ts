import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { CreateProtocol, Protocol } from '@libs/sdk/protocol';


@Component({
  selector: 'tsw-protocol-see-page',
  templateUrl: './protocol-see-page.component.html',
  styleUrls: ['./protocol-see-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ProtocolSeePageComponent extends EditPageBaseComponent<Protocol, CreateProtocol> implements OnInit{

  readonly resourceName = 'protocol';
  protected override _createResourceTitle = 'pages.protocol.add';
  protected override _editResourceTitle = 'pages.protocol.edit';

  protected buildForm(): FormGroup {
    const form = this.fb.group({
      name: [{ value: null, disabled: true }],
      code: [{ value: null, disabled: true }],
      campaignId: [{ value: null, disabled: true }],
      nameCampaign: [{ value: null, disabled: true }],
      question: this.fb.array([])
    });

    // Usar setTimeout para agregar la primera fila
    setTimeout(() => {
      const questionsControl = form.get('question') as FormArray;
      questionsControl.push(this.crearFila(1));
    }, 0);

    console.log('form', form.get('nameCampaign')?.value);
    return form;
  }

  crearFila(orden: number): FormGroup {
    console.log('crearFila', orden);
    return this.fb.group({
      id: { value: null, disabled: true },
      orderQuestion: [{ value: orden, disabled: true }],
      codeQuestion: { value: null, disabled: true },
      question: [{ value: '', disabled: true }, Validators.required],
      codeInfringement: { value: null, disabled: true },
      response: [{ value: 'SI', disabled: true }] // Inicializar con 'SI'
    });
  }

  get question() {

    return this.form.get('question') as unknown as FormArray;
  }

}
