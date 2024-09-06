import { Component, inject, OnInit } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { Location } from '@angular/common';
import { CampaignIpr } from '@libs/sdk/campaign';
import { firstValueFrom } from 'rxjs/internal/firstValueFrom';
import { ProtocolQuestionDialogComponent } from '@base/pages/protocolQuestion-dialog/protocolQuestion-dialog.component';
import { Validator } from '@base/shared/functions/validators';


@Component({
  selector: 'tsw-ipr',
  templateUrl: './ipr-edit-page.component.html',
  styleUrls: ['./ipr-edit-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})

export class IprEditPageComponent extends EditPageBaseComponent<any, CampaignIpr> implements OnInit{

  campaign: any;
  readonly resourceName = 'ipr';
  name: string | null = ''; // Variable para almacenar el nombre de la campaña
  campaignId: number | null = null; // Variable para almacenar el id de la campaña
  private location: Location = inject(Location);
  idCampaign: number | null = null;
  protocolSelectedId: string | undefined;
  protocolSelectedCode: string | undefined
  protocolArray: any;
  responseUser: any;
  respuestasUsuarioCombined: any[] = [];
  iprQuestionDTOList: any[] = []; // Declaración de la propiedad iprQuestionDTOList
  cancelRedirectPath = '../../ver';

  initialFormValues: any;

  idIpr: any;

  protocolIdLoad: any;
  protocolCodeLoad: any

  override async ngOnInit(): Promise<void> {
    super.ngOnInit();
    this.route.params.subscribe(params => {
      this.idCampaign = params['idCampaña']; 
      this.idIpr = params['id'];
    });
    await this.fetchProtocol();
  }

  protected buildForm(): FormGroup {

    const form = this.fb.group({
      nameCampaign: { value: null, disabled: true },
      year: { value: null, disabled: true },
      name: this.fb.control(null,[Validators.required, Validators.maxLength(100)]),
      protocols: this.fb.control(null,[Validators.required]),
      question: this.fb.array([]),
      formula: null,
      porcentaje: null,
    });

      
    return form;
  }

  handleKeydown(event: KeyboardEvent, index: number): void {
    if (event.key === 'Enter' || event.key === ' ') {
      event.preventDefault(); // Prevenir comportamiento predeterminado como desplazarse en el caso de la tecla Espacio
      this.openDialog(index);
    }
  }

  override resetForm()
  {
    this.fetchProtocol();
    this.form.reset(this.initialFormValues);
  }

  protected override afterLoadDataSuccess(startValue: any): void {

    const questionArrayLoad = startValue.iprQuestionDTOList;
    const questionsControl = this.form.get('question') as FormArray;
    questionArrayLoad.forEach((question:any) => {
      questionsControl.push(this.crearFila2(question));
    });

    this.protocolIdLoad = startValue.protocolId;
    this.protocolCodeLoad = startValue.protocolCode;

    // Guardar los valores iniciales del formulario
    this.initialFormValues = this.form.getRawValue();
    this.initialFormValues.nameCampaign = startValue.nameCampaign;
    this.initialFormValues.year = startValue.year;
    this.initialFormValues.name = startValue.name;

    super.afterLoadDataSuccess(startValue);
  }
  
  private async fetchProtocol(): Promise<void> {
    const id = Number(this.idCampaign);
    this.protocolArray = await firstValueFrom(this.crudService.findById(id, {
      resourceName: 'protocolListCampaign',
      pathParams: { id },
    }));
    // Primero busca el protocolo por code
    let selectedProtocol = this.protocolArray.find((protocol: any) => protocol.code === this.protocolCodeLoad);

    // Si no se encuentra por code, busca por id
    if (!selectedProtocol) {
      selectedProtocol = this.protocolArray.find((protocol: any) => protocol.id === this.protocolIdLoad);
    }
  
    // Verifica si se encontró el protocolo y luego establece el valor en el formulario
    if (selectedProtocol) {

      this.protocolSelectedId = selectedProtocol.id;
      this.protocolSelectedCode = selectedProtocol.code;

      setTimeout(() => {
        this.form.patchValue({
          protocols: selectedProtocol
        });
      }, 0);
    }
  }


  onProtocolSelectionChange(value: any) {
    this.protocolSelectedId = value.id;
    this.protocolSelectedCode = value.code;
    const protocolData = { id: value.id, code: value.code };
  }

  crearFila(orden: number): FormGroup {
    console.log('al ingresar a IPR esta es la primera fila de pregunta que se crea', orden);
    return this.fb.group({
      id: null,
      orderQuestion: [{ value: orden, disabled: true }],
      question: this.fb.control(null,[Validators.required]),
      formula: this.fb.control(null,[Validators.required]),
      porcentaje: this.fb.control(null, Validator.validateNumber()),
    });
  }

  crearFila2(data: any): FormGroup {
    return this.fb.group({
      id: data?.id || null,
      orderQuestion: [{ value: data?.orderQuestion, disabled: true }],
      question: [data?.question || null, Validators.required],
      formula: [data?.formula || null, Validators.required],
      porcentaje: [data?.percentageRespectTo || null, Validator.validateNumber()],
    });
  }

  override getRedirectAfterSaveRoute(){
    return ['../consulta'];
  }

  openDialog(rowIndex: number): void {

    const protocolData = { id: String(this.protocolSelectedId), code: String(this.protocolSelectedCode) };
    console.log('protocolData', protocolData)
    this.sharedDataService.updateSharedData(protocolData);
    console.log('protocolData', protocolData)

    const dialogRef = this.dialog.open(ProtocolQuestionDialogComponent, {
      width: '95%',
      height: '95%',
      maxWidth: '100vw',
      maxHeight: '100vh',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && result.length > 0) {
        const selectedItem = result[0]; // Si esperas un solo objeto, ajusta según sea necesario
        console.log('selectedItem', selectedItem);
        this.updateFormRowWithSelectedItem(rowIndex, selectedItem);
      }

      this.sharedDataService.sharedData$.subscribe(data => {
        this.responseUser = data;
      });
      //console.log('datos respuesta recibidos: ' + JSON.stringify(this.responseUser))
      this.createCombinedFormula(this.responseUser, rowIndex);
    });
  }


  protected createCombinedFormula(respuestas: any[], rowIndex: number){
    
    this.respuestasUsuarioCombined = respuestas;
    let stringCombinado = '';

    console.log(rowIndex);
  
    this.respuestasUsuarioCombined.forEach((respuesta, index) => {
      const respuestaValue = respuesta.respuesta ?? ''; // Si respuesta.respuesta es undefined, se asigna un espacio en blanco
      const preguntaValue = respuesta.pregunta ?? ''; // Si respuesta.pregunta es undefined, se asigna un espacio en blanco
      
      stringCombinado += respuestaValue;
      
      if (index < this.respuestasUsuarioCombined.length - 1) {
        stringCombinado += ' + ';
      }
    });
  
    // Obtener el identificador único del input de fórmula
    const formulaInputId = 'formulaInput_' + rowIndex;
  
    // Establecer el valor de stringCombinado en el input de fórmula correspondiente
    const formulaInputElement = document.getElementById(formulaInputId) as HTMLInputElement;

    const question = this.form.get('question');

    if (question instanceof FormArray) {
      const control = question.at(rowIndex)?.get('formula');

      if (control) {
        const truncatedString = stringCombinado.length > 5 ? stringCombinado.substring(0, 10) + '...' : stringCombinado;
        control.setValue(truncatedString);
        formulaInputElement.title = stringCombinado;
      } else {
        console.error('FormControl "formula" no encontrado en el control:', control);
      }
    } else {
      console.error('No se encontró un FormArray con el nombre "question" en el formulario.');
    }
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

  get question() {
    return this.form.get('question') as unknown as FormArray;
  }

  agregarFila() {
    const questionsControl = this.form.get('question') as unknown as FormArray;
    const nuevoOrden = questionsControl.length + 1;
    questionsControl.push(this.crearFila(nuevoOrden));
  }

  agregarFilaDespuesDe(index: number) {
    const questionsControl = this.form.get('question') as FormArray;
    const nuevoOrden = questionsControl.length + 1;
    questionsControl.insert(index + 1, this.crearFila(nuevoOrden));
    
    this.refreshOrder();
  }

  eliminarFila(index: number) {
    // Verifica si hay más de una fila antes de eliminar
    if (this.question.length > 1) {
        // Elimina la fila en el índice dado
        this.question.removeAt(index);

        // Actualizar orden
        this.refreshOrder();
    } else {
        // Opcional: Puedes mostrar un mensaje indicando que la fila precargada no se puede eliminar
        console.warn("La fila precargada no se puede eliminar.");
         this.notification.show({
          title: 'text.err.questionEraseFailed',
          message: 'No se puede crear un IPR sin preguntas',
        });
    }
  }

  refreshOrder(){
    // Recorre todas las filas restantes para actualizar el campo 'orderQuestion'
    this.question.controls.forEach((control, i) => {
      control.get('orderQuestion')?.setValue(i + 1);
    });

  }


  toggleResp(filaIndex: number) {
    const fila = (this.form.get('question') as unknown as FormArray).at(filaIndex) as FormGroup;
    const currentValue = fila.get('response')?.value;
    fila.get('response')?.setValue(currentValue === 'SI' ? 'NO' : 'SI');
  }

  prepareIprQuestionDTOList() {

    const questionControl = this.form.get('question');
    if (questionControl !== null && questionControl instanceof FormArray) {
      const questionArray = questionControl as FormArray;
      questionArray.controls.forEach((control) => {

        const formulaQuestion = control.get('formula')?.value;

        console.log('formulario recibido' + formulaQuestion)

        const newQuestion = {
          orderQuestion: control.get('orderQuestion')?.value, 
          percentageRespectTo: control.get('porcentaje')?.value, 
          formula: control.get('formula')?.value, 
          question: control.get('question')?.value, 
        };
        this.iprQuestionDTOList.push(newQuestion);
      });
      console.log(this.iprQuestionDTOList); // Muestra los datos en la consola para verificar
    } else {
      console.error('FormArray "question" not found in form controls.');
    }
  }


  saveForm() {
      // Verificar si el formulario es válido
    if (this.form.invalid) {
      console.log('El formulario no es válido. No se puede guardar.');
      return; // Salir de la función si el formulario no es válido
    }
    console.log('guardamos');
    this.prepareIprQuestionDTOList();
    const jsonData = {
      id: this.idIpr,
      name: this.form.get('name')?.value,
      protocolCode: this.protocolSelectedCode,
      campaignId: this.idCampaign,
      protocolId: this.protocolSelectedId,
      iprQuestionDTOList: this.iprQuestionDTOList
    };

    this.crudService.update(this.idIpr, jsonData, {
      resourceName: 'ipr',
      pathParams: { id: this.idIpr }
    })
    .subscribe(
      response => {
        this.notification.show({
          title: 'text.other.dataSaved',
          message: 'IPR creado exitosamente',
        });

        // Redirigir a la nueva URL
        const newUrl = `/app/campanas/${this.idCampaign}/ver`;
        this.router.navigate([newUrl]);


      },
      error => {
        console.error('Error al crear IPR:', error);

        this.notification.show({
          title: 'Error',
          message: 'Ha ocurrido un error',
        });
      }
    );
  }
}
