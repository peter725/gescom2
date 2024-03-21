import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { CreateProtocol, Protocol } from '@libs/sdk/protocol';
import { InfringementDialogComponent} from '@base/pages/infringement-dialog/infringement-dialog.component';
import { firstValueFrom } from 'rxjs';


@Component({
  selector: 'tsw-protocol-copy-page',
  templateUrl: './protocol-copy-page.component.html',
  styleUrls: ['./protocol-copy-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ProtocolCopyPageComponent extends EditPageBaseComponent<Protocol, CreateProtocol> implements OnInit{

  readonly resourceName = 'protocol';
  protected override _createResourceTitle = 'pages.protocol.add';
  protected override _editResourceTitle = 'pages.protocol.edit';

  campaignIdShared = 0;

  override ngOnInit(): void {
    super.ngOnInit();
  
    // Obtener el campaignIdShared del localStorage
    const campaignIdSharedString = localStorage.getItem('campaignIdShared');
    this.campaignIdShared = campaignIdSharedString ? parseInt(campaignIdSharedString, 10) : 0;
  
    // Suscribirse al evento sharedData$
    this.sharedDataService.sharedData$.subscribe(data => {
      this.campaignIdShared = data?.campaignId;
      if(this.campaignIdShared != undefined){
      localStorage.setItem('campaignIdShared', String(this.campaignIdShared));
      }
      console.log('Campaign ID shared service copy:', this.campaignIdShared);
    });
  }

  protected buildForm(): FormGroup {
    const form = this.fb.group({
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

  openDialog(rowIndex: number): void {
    const dialogRef = this.dialog.open(InfringementDialogComponent, {
      width: '75%',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && result.length > 0) {
        const selectedItem = result[0]; 
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
  

  // Sobrescribir el método isNew para siempre devolver true
  override get isNew() {
    return true; // Siempre considerar como nueva instancia
  }

  // Método para cargar los datos desde el endpoint
  override  async loadData() {
    try {
      this.resetDataBeforeLoad();
      let startValue: any;

      this.srcData = await firstValueFrom(this.fetchExistingSrc());
      startValue = await firstValueFrom(this.mapModelToForm(this.srcData));     
      
      this.afterLoadDataSuccess(startValue);


    const questions = this.form.get('question') as unknown as FormArray; 
    this.srcData.question.forEach((q: any) => {
      questions.push(this.loadRowQuestion(q.orderQuestion, q.codeQuestion, q.question, q.codeInfringement, q.response));
    });


    } catch (err: any) {
      this.afterLoadDataError(err);
    }
  }

  loadRowQuestion(orden: number, codeQuestion: string, question: string, codeInfringement: string, response: string): FormGroup {

    const formattedResponse = response === 'N' ? 'NO' : response === 'S' ? 'SI' : response;

    return this.fb.group({
      id: null,
      orderQuestion: [{ value: orden, disabled: true }],
      codeQuestion: codeQuestion,
      question: [question, Validators.required],
      codeInfringement: codeInfringement,
      response: formattedResponse
    });
  }

  saveCopyProtocol(){
    this.form.patchValue({
      campaignId: this.campaignIdShared
    });
     this.save();
  }


  protected override async afterSaveSuccess(result: any) {


    // Redirige a la página anterior
    this.redirectAfterSavePath = ['/app/campanas', String(this.campaignIdShared), 'ver'];

    await super.afterSaveSuccess(result); 

   
  }



}
