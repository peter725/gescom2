import { Component } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { CustomValidators } from '@libs/validators';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { CreateProtocol, Protocol } from '@libs/sdk/protocol';

@Component({
  selector: 'app-protocol-add-page',
  templateUrl: './protocol-add-page.component.html',
  styleUrls: ['./protocol-add-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' }},
  ]
})
export class ProtocolAddPageComponent extends EditPageBaseComponent<Protocol, CreateProtocol> {


  readonly resourceName = 'protocol';
  protected override _createResourceTitle = 'pages.protocol.add';
  protected override _editResourceTitle = 'pages.protocol.edit';

  protected buildForm(){
    return this.fb.group<ControlsOf<CreateProtocol>>({
      rows: this.fb.array([]) as any,
    });
  }

  override ngOnInit() {
    this.route.paramMap.subscribe(params => {
      let campaignId = params.get('campaignId');
      // Usa campaignId como necesites
    });
  }

  get rows() {
    return this.form.get('rows') as unknown as FormArray;
  }

  agregarFila() {
    const rows = this.fb.group({
      Orden: '',
      CodPregunta: '',
      Pregunta: '',
      Infraccion: '',
      Resp: ''
    });
    this.rows.push(rows);
  }

  eliminarFila(index: number) {
    this.rows.removeAt(index);
  }


}
