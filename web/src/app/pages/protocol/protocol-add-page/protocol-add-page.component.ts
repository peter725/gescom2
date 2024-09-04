import { Component, inject, OnInit, Output } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { CreateProtocol, Protocol } from '@libs/sdk/protocol';
import { InfringementDialogComponent} from '@base/pages/infringement-dialog/infringement-dialog.component';
import { DataSharingService } from '@base/services/dataSharingService';
import { Location } from '@angular/common';


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
  cancelRedirectPath = '../../protocol/consulta';

  private dataSharingService: DataSharingService = inject(DataSharingService);
  name: string | null = ''; // Variable para almacenar el nombre de la campaña
  campaignId: number | null = null; // Variable para almacenar el id de la campaña
  private location: Location = inject(Location);

  override ngOnInit(): void {
    super.ngOnInit();
    this.subscribeToCampaignData();
    
  }

  ngAfterViewInit(): void {
    let campaignId = this.form.get('campaignId')?.value;
    this.cancelRedirectPath = campaignId ? `../../campanas/${campaignId}/ver` : '../../protocol/consulta';
  }

  override getRedirectAfterSaveRoute(){
    return ['../consulta'];
  }

  handleKeydown(event: KeyboardEvent, index: number): void {
    if (event.key === 'Enter' || event.key === ' ') {
      event.preventDefault(); // Prevenir comportamiento predeterminado como desplazarse en el caso de la tecla Espacio
      this.openDialog(index);
    }
  }

  private subscribeToCampaignData(): void {
    const storedCampaign = localStorage.getItem('currentCampaign');

    if (storedCampaign) {
      const campaignData = JSON.parse(storedCampaign);
      this.loadCampaignData(campaignData);
    } else {
      this.dataSharingService.currentCampaign.subscribe(campaignData => {
        if (campaignData) {
          localStorage.setItem('currentCampaign', JSON.stringify(campaignData));
          this.loadCampaignData(campaignData);
        }
      });
    }
  }

  private loadCampaignData(campaignData: any) {
    this.name = campaignData.nameCampaign;
    this.campaignId = campaignData.id;
    this.form.patchValue({
      campaignId: campaignData.id,
      nameCampaign: campaignData.nameCampaign
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

  agregarFilaDespuesDe(index: number) {
    const questionsControl = this.form.get('question') as unknown as FormArray;
    const nuevoOrden = questionsControl.length + 1;
    questionsControl.insert(index + 1, this.crearFila(nuevoOrden));
    
    this.refreshOrder();
  }

  
  refreshOrder(){

    // Recorre todas las filas restantes para actualizar el campo 'orderQuestion'
    this.question.controls.forEach((control, i) => {
      control.get('orderQuestion')?.setValue(i + 1);
    });

  }

  updateFormRowWithSelectedItem(rowIndex: number, selectedItem: any) {
    const questions = this.form.get('question') as unknown as FormArray;
    if (questions.at(rowIndex)) {
      const question = questions.at(rowIndex) as FormGroup;
      question.patchValue({
        codeInfringement: selectedItem.code,
      });
      console.log('row', question);
    }
  }

  protected buildForm(): FormGroup {
    const form = this.fb.group({
      name: [null, Validators.required],
      // code: [{ value: null, disabled: true }, ],
      campaignId: [ null, Validators.required],
      question: this.fb.array([])
    });

    // Usar setTimeout para agregar la primera fila
    setTimeout(() => {
      const questionsControl = form.get('question') as FormArray;
      questionsControl.push(this.crearFila(1));
    }, 0);

    return form;
  }

  crearFila(orden: number): FormGroup {
    console.log('crearFila', orden);
    return this.fb.group({
      id: null,
      orderQuestion: [{ value: orden, disabled: true }],
      codeQuestion: ['', Validators.required],
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

    //actualizar orden 
    this.refreshOrder();
  }


  toggleResp(filaIndex: number) {
    const fila = (this.form.get('question') as unknown as FormArray).at(filaIndex) as FormGroup;
    const currentValue = fila.get('response')?.value;
    fila.get('response')?.setValue(currentValue === 'SI' ? 'NO' : 'SI');
  }

  async saveForm() {
    if (this.form.invalid) {
      this.notification.show({ message: 'text.other.pleaseReview' });
    } else {
      super.setRedirectAfterSave(false);
      await this.save();

      this.location.back();
    }
    
  }

}
