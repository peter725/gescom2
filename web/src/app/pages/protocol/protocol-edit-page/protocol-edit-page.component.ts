import { Component, OnInit, inject } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { CreateProtocol, Protocol } from '@libs/sdk/protocol';
import { InfringementDialogComponent} from '@base/pages/infringement-dialog/infringement-dialog.component';
import { firstValueFrom } from 'rxjs';
import { DataSharingService } from '@base/services/dataSharingService';
import { Location } from '@angular/common';


@Component({
  selector: 'tsw-protocol-edit-page',
  templateUrl: './protocol-edit-page.component.html',
  styleUrls: ['./protocol-edit-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ProtocolEditPageComponent extends EditPageBaseComponent<Protocol, CreateProtocol> implements OnInit{

  readonly resourceName = 'protocol';
  protected override _createResourceTitle = 'pages.protocol.add';
  protected override _editResourceTitle = 'pages.protocol.edit';
  cancelRedirectPath = '../../campanas/consulta';

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
    this.cancelRedirectPath = campaignId ? `../../campanas/${campaignId}/ver` : '../../campanas/consulta';
  }

  private subscribeToCampaignData(): void {
    this.dataSharingService.currentCampaign.subscribe(campaignData => {
      if (campaignData) {
        this.name = campaignData.nameCampaign;
        this.campaignId = campaignData.id;
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

  protected buildForm(): FormGroup {
    const form = this.fb.group({
      id: null,
      name: [null, Validators.required],
      // code: [{ value: null, disabled: true }],
      campaignId: [{ value: null, disabled: true }],
      nameCampaign: [{ value: null, disabled: true }],
      question: this.fb.array([])
    });

    // // Usar setTimeout para agregar la primera fila
    // setTimeout(() => {
    //   const questionsControl = form.get('question') as FormArray;
    //   questionsControl.push(this.crearFila(1));
    // }, 0);

  

    return form;
  }

  // protected override async afterLoadDataSuccess(result: any) {

  //   super.afterLoadDataSuccess(result);

  //   const questions = this.form.get('question') as unknown as FormArray; 
  //   if (this.srcData && this.srcData.question) {
  //   this.srcData.question.forEach((q: any) => {

  //     questions.push(this.loadRowQuestion(q.orderQuestion, q.codeQuestion, q.question, q.codeInfringement, q.response));
  //   });
  // }


  // }

    // Método para cargar los datos desde el endpoint
    override async loadData() {


      try {
        this.resetDataBeforeLoad();
    
        // Cargar datos
        this.srcData = await firstValueFrom(this.fetchExistingSrc());
        const startValue = await firstValueFrom(this.mapModelToForm(this.srcData));
        this.afterLoadDataSuccess(startValue);
    
        // Ordenar las preguntas según orderQuestion
        const questions = this.form.get('question') as unknown as FormArray;
        this.srcData.question.sort((a: any, b: any) => a.orderQuestion - b.orderQuestion);
        this.srcData.question.forEach((q: any) => {
          questions.push(this.loadRowQuestion(q.orderQuestion, q.codeQuestion, q.question, q.codeInfringement, q.response));
        });
        console.log('Questions Protocol-edit', questions)
      } catch (err: any) {
        this.afterLoadDataError(err);
      }
    }

  loadRowQuestion(orden: number, codeQuestion: string, question: string, codeInfringement: string, response: string): FormGroup {
    const formattedResponse = response === 'N' ? 'NO' : response === 'S' ? 'SI' : response;
    return this.fb.group({
      id: null,
      orderQuestion: [{ value: orden, disabled: true }],
      codeQuestion: [codeQuestion, Validators.required],
      question: [question, Validators.required],
      codeInfringement: codeInfringement,
      response: formattedResponse // Inicializar con 'SI'
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
