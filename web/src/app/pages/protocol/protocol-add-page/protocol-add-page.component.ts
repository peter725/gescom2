import { Component, inject, OnInit, Output } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { CreateProtocol, Protocol } from '@libs/sdk/protocol';
import { InfringementDialogComponent} from '@base/pages/infringement-dialog/infringement-dialog.component';
import { DataSharingService } from '@base/services/dataSharingService';



@Component({
  selector: 'tsw-protocol-add-page',
  templateUrl: './protocol-add-page.component.html',
  styleUrls: ['./protocol-add-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ProtocolAddPageComponent extends EditPageBaseComponent<Protocol, CreateProtocol> implements OnInit{

  readonly resourceName = 'protocol';
  protected override _createResourceTitle = 'pages.protocol.add';

  private dataSharingService: DataSharingService = inject(DataSharingService);
  name: string | null = ''; // Variable para almacenar el nombre de la campaña
  campaignId: number | null = null; // Variable para almacenar el id de la campaña


  override ngOnInit(): void {
    super.ngOnInit();
    this.subscribeToCampaignData();
  }


  override getRedirectAfterSaveRoute(){
    return ['../consulta'];
  }

  private subscribeToCampaignData(): void {
    this.dataSharingService.currentCampaign.subscribe(campaignData => {
      console.log('campaignData', campaignData);
      if (campaignData) {
        this.name = campaignData.nameCampaign;
        this.campaignId = campaignData.id;
        console.log('campaignData', this.name);
        // Aquí configuras los datos de la campaña en el formulario de protocolo
        // Por ejemplo, podrías querer establecer el valor de algún campo basado en campaignData
        this.form.patchValue({
          campaignId: campaignData.id,
          nameCampaign: campaignData.nameCampaign,// Asume que el formulario tiene un campo 'campaign'
          // Puedes agregar más campos aquí si es necesario
        });
      }
    });
  }


  openDialog(rowIndex: number): void {
    const dialogRef = this.dialog.open(InfringementDialogComponent, {
      width: '75%',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && result.length > 0) {
        const selectedItem = result[0]; // Si esperas un solo objeto, ajusta según sea necesario
        console.log('selectedItem', selectedItem);
        this.updateFormRowWithSelectedItem(rowIndex, selectedItem);
      }
    });
  }

  updateFormRowWithSelectedItem(rowIndex: number, selectedItem: any) {
    const questions = this.form.get('question') as unknown as FormArray;
    if (questions.at(rowIndex)) {
      const question = questions.at(rowIndex) as FormGroup;
      question.patchValue({
        // Suponiendo que el campo se llama 'infringement' en tu formulario y 'nombre' en tu objeto seleccionado
        codeInfringement: selectedItem.code,
        // Aquí puedes actualizar otros campos relevantes
      });
      console.log('row', question);
    }
  }

  protected buildForm(): FormGroup {
    const form = this.fb.group({
      name: [null, Validators.required],
      code: [null, ],
      campaignId: [ null, Validators.required],
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
      id: null,
      orderQuestion: [{ value: orden, disabled: true }],
      codeQuestion: null,
      question: ['', Validators.required],
      codeInfringement: null,
      response: ['SI'] // Inicializar con 'SI'
    });
  }


  get question() {
    return this.form.get('question') as unknown as FormArray;
  }

  agregarFila() {
    const questionsControl = this.form.get('question') as unknown as FormArray;
    const nuevoOrden = questionsControl.length + 1;
    questionsControl.push(this.crearFila(nuevoOrden));
  }

  eliminarFila(index: number) {
    // Elimina la fila en el índice dado
    this.question.removeAt(index);

    // Recorre todas las filas restantes para actualizar el campo 'orden'
    this.question.controls.forEach((control, i) => {
      console.log('control', control);
      control.get('order')?.setValue(i + 1);
    });
  }


  toggleResp(filaIndex: number) {
    const fila = (this.form.get('question') as unknown as FormArray).at(filaIndex) as FormGroup;
    const currentValue = fila.get('response')?.value;
    fila.get('response')?.setValue(currentValue === 'SI' ? 'NO' : 'SI');
  }

  saveForm() {
    if (this.form.invalid) {
      this.notification.show({ message: 'text.other.pleaseReview' });
    } else {
      this.submitForm();
    }
    
  }

}
