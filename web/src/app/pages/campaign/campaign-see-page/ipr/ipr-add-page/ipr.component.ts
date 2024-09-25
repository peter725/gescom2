import { Component,  inject, OnInit} from '@angular/core';
import { FormArray,  FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { Location } from '@angular/common';
import { CampaignIpr } from '@libs/sdk/campaign';
import { firstValueFrom } from 'rxjs/internal/firstValueFrom';
import { ProtocolQuestionDialogComponent } from '@base/pages/protocolQuestion-dialog/protocolQuestion-dialog.component';
import { Validator } from '@base/shared/functions/validators';
import { RequestParams } from '@libs/crud-api';

interface RequestConfig {
  resourceName: string;
  pathParams?: any;
}

@Component({
  selector: 'tsw-ipr',
  templateUrl: './ipr.component.html',
  styleUrls: ['./ipr.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})

export class IprComponent extends EditPageBaseComponent<any, CampaignIpr> implements OnInit{

  campaign: any;
  readonly resourceName = 'campaign';
  name: string | null = ''; // Variable para almacenar el nombre de la campaña
  campaignId: number | null = null; // Variable para almacenar el id de la campaña
  //private location: Location = inject(Location);
  idCampaign!: number;
  protocolSelectedId: number | undefined;
  protocolSelectedCode: string | undefined;
  protected protocolSelectQuery!: RequestParams;
  responseUser: any;
  respuestasUsuarioCombined: any[] = [];
  iprQuestionDTOList: any[] = []; // Declaración de la propiedad iprQuestionDTOList
  cancelRedirectPath = '../ver';

  override async ngOnInit(): Promise<void> {
    super.ngOnInit();

    this.route.params.subscribe(params => {
      this.idCampaign = params['id']; // 'id' debe coincidir con el nombre del parámetro en la ruta
      console.log('id campaña' + this.idCampaign); // aquí puedes hacer lo que necesites con el ID recuperado
    });
    this.protocolSelectQuery = {
        campaignId: this.idCampaign
    };
    //await this.fetchProtocol();
    console.log('Patched form values:ONINIT', this.form.get('protocols')?.value);
    //console.log(this.fetchProtocol())
  }



  handleKeydown(event: KeyboardEvent, index: number): void {
    if (event.key === 'Enter' || event.key === ' ') {
      event.preventDefault(); // Prevenir comportamiento predeterminado como desplazarse en el caso de la tecla Espacio
      this.openDialog(index);
    }
  }

  /*private async fetchProtocol(): Promise<any> {
    console.log('Patched form values:BEFORE', this.form.get('protocols')?.value);
    const id = Number(this.idCampaign);
    this.protocolArray = await firstValueFrom(this.crudService.findById(id, {
      resourceName: 'protocolListCampaign',
      pathParams: { id },
    }));
    console.log('Patched form values:AFTER', this.form.get('protocols')?.value);
  }*/

  onProtocolSelectionChange() {

    console.log('form.protocol', typeof this.form.controls['protocol'].value?.id);
    //const [id, code] = value.split('-');
    this.protocolSelectedId = this.form.controls.protocol.value?.id;
    this.protocolSelectedCode = this.form.controls.protocol.value?.code;
    const protocolData = { id: this.protocolSelectedId };
  }
  
  protected buildForm(): FormGroup {
    const form = this.fb.group({
      nameCampaign: { value: null, disabled: true },
      year: { value: null, disabled: true },
      name: this.fb.control(null,[Validators.required, Validators.maxLength(100)]),
      protocol: this.fb.control({ value: null, disabled: false },[Validators.required]),
      question: this.fb.array([]),
      formula: null,
      porcentaje: null,
    });

    // Usar setTimeout para agregar la primera fila
    setTimeout(() => {
      console.log('Patched form values:SETTIMEOUT', this.form.get('protocols')?.value);
      const questionsControl = form.get('question') as FormArray;
      questionsControl.push(this.crearFila(1));
    }, 0);
    console.log('Patched form values:IN FORM', form.get('protocols')?.value);
    return form;
  }

  crearFila(orden: number): FormGroup {
    console.log('Patched form values:CREAR FILA', this.form.get('protocols')?.value);
    return this.fb.group({
      id: null,
      orderQuestion: [{ value: orden, disabled: true }],
      question: this.fb.control(null,[Validators.required]),
      formula: this.fb.control(null,[Validators.required]),
      porcentaje: this.fb.control(null, Validator.validateNumber()),
    });
  }


  override getRedirectAfterSaveRoute(){
    return ['../consulta'];
  }

  openDialog(rowIndex: number): void {
    const protocolData = { id: this.protocolSelectedId , code: String(this.protocolSelectedCode) };
    this.sharedDataService.updateSharedData(protocolData);

    const dialogRef = this.dialog.open(ProtocolQuestionDialogComponent, {
      width: '95%',
      height: '95%',
      maxWidth: '100vw',
      maxHeight: '100vh',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && result.length > 0) {
        const selectedItem = result[0];
        console.log('selectedItem', selectedItem);
        this.updateFormRowWithSelectedItem(rowIndex, selectedItem);
      }
      this.sharedDataService.sharedData$.subscribe(data => {
        this.responseUser = data;
      });
      this.createCombinedFormula(this.responseUser, rowIndex);
    });
  }


  protected createCombinedFormula(respuestas: any[], rowIndex: number){
    console.log('Respuesta', respuestas);
    this.respuestasUsuarioCombined = respuestas;
    let stringCombinado = '';
    this.respuestasUsuarioCombined.forEach((respuesta, index) => {
      const respuestaValue = respuesta.respuesta ?? ''; // Si respuesta.respuesta es undefined, se asigna un espacio en blanco
      const preguntaValue = respuesta.pregunta ?? ''; // Si respuesta.pregunta es undefined, se asigna un espacio en blanco
      
      stringCombinado += respuestaValue;
      if (index < this.respuestasUsuarioCombined.length - 1) {
        stringCombinado += ' + ';
      }
    });
    console.log('StringCombinado 1', stringCombinado);
    // Obtener el identificador único del input de fórmula
    const formulaInputId = 'formulaInput_' + rowIndex;
  
    // Establecer el valor de stringCombinado en el input de fórmula correspondiente
    const formulaInputElement = document.getElementById(formulaInputId) as HTMLInputElement;

    const question = this.form.get('question');

    if (question instanceof FormArray) {
      const control = question.at(rowIndex)?.get('formula');

      if (control) {
        control.setValue(stringCombinado);
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
    // Verifica si el campo 'protocols' es inválido
  console.log('Protocol value:', this.form.get('protocols')?.value);
  console.log('Is protocols invalid:', this.form.get('protocols')?.invalid);
  console.log('Form status:', this.form.status);

    // Verificar si el formulario es válido
    if (this.form.invalid) {
      return this.notification.show({
        title: 'text.err.questionEraseFailed',
        message: 'El formulario no es válido. No se puede guardar.',
      }); // Salir de la función si el formulario no es válido
    }

    this.prepareIprQuestionDTOList();

    const jsonData = {
      name: this.form.get('name')?.value,
      campaignId: this.idCampaign,
      protocol: this.form.get('protocol')?.value,
      iprQuestionDTOList: this.iprQuestionDTOList
    };
    const config: RequestConfig = {
      resourceName: 'ipr', 
    };

    this.crudService.create(jsonData, config)
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
        this.notification.show({
          title: 'Error',
          message: 'Ha ocurrido un error',
        });
      }
    );
  }
}
