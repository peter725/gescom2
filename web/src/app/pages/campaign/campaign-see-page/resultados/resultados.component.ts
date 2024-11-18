import { Component, inject, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { CreateProtocol, Protocol, Question } from '@libs/sdk/protocol';
import { InfringementDialogComponent} from '@base/pages/infringement-dialog/infringement-dialog.component';
import { DataSharingService } from '@base/services/dataSharingService';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Campaign, CampaignForm } from '@libs/sdk/campaign';
import { AutonomousCommunity } from '@libs/sdk/autonomousCommunity';
import { CampaignProductServiceDTO } from '@libs/sdk/productService';
import { ProtocolResults, TotalProtocolResults } from '@libs/sdk/protocolResults';
import { ProtocolResultsService } from '@base/shared/utilsService/protocolResults.service';
import { NotificationService } from '@base/shared/notification';
import { AuthContextService } from '@libs/security';
import { Validator } from '@base/shared/functions/validators';


@Component({
  selector: 'tsw-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ResultadosComponent implements OnInit{

  readonly resourceName = 'protocol';
  cancelRedirectPath = '../../campanas/consulta';

  codNumExistentes: string = 'DC1';
  codNumControlados: string = 'DC8';
  codProdControlados: string = 'DC9';
  codProdCorrectos: string = 'DC10';
  codProdIncorrectos: string = 'DC11';

  private dataSharingService: DataSharingService = inject(DataSharingService);
  name: string | null = ''; // Variable para almacenar el nombre de la campaña
  campaignId: number | null = null; // Variable para almacenar el id de la campaña
  private location: Location = inject(Location);
  campaign: CampaignForm | any;
  protocolosList: Protocol[] = [];
  productosList: CampaignProductServiceDTO[] = [];
  caList: AutonomousCommunity[] = [];
  totalProductosControlados: any;

  protocoloSelected: any;
  productoSelected: any;
  caSelected: any;
  canModify = true;

  preguntasProtocolo: Question [] = [];

  resultadoSelected: ProtocolResults | undefined = undefined;

  userAutonomousCommunity: string = "";

  editForm1 = this.fb.group({
    id: [],
    campania: this.fb.group<ControlsOf<CampaignForm>>({
      id: this.fb.control({ value: null, disabled: true }),
      year: this.fb.control({ value: null, disabled: true }),
      codeCpa: this.fb.control({ value: null, disabled: true }),
      nameCampaign: this.fb.control({ value: null, disabled: true }),
      campaignType: this.fb.control({ value: null, disabled: true }),
      participants: this.fb.control({ value: [], disabled: true }),
      ambit: this.fb.control({ value: null, disabled: true }),
      specialists: this.fb.control({ value: [], disabled: true }),
      proponents: this.fb.control({ value: [], disabled: true }),
      autonomousCommunityResponsible: this.fb.control({ value: null, disabled: true }),
      phaseCampaign: this.fb.control({ value: null, disabled: true }),
      createdAt: this.fb.control({ value: null, disabled: true }),
      updatedAt: this.fb.control({ value: null, disabled: true }),
      state: this.fb.control({ value: null, disabled: true }),
      protocols: this.fb.control({ value: [], disabled: true }),
      campaignProductServiceDTOS: this.fb.control({ value: [], disabled: true }),
    }),
    protocolo: [null, Validators.required],
    producto: [],
    ca: [] as AutonomousCommunity[],
    // producto: [null, [Validators.required, Validators.maxLength(50)]],
  });

  editForm2 = this.fb.group({
    numExistentes: [],
    numControlados: [ ,[Validators.required, Validators.pattern('^[0-9]*$')]],
    totalProdControlados: [ , [Validators.required, Validators.pattern('^[0-9]*$')]],
    totalProdCorrectos: [ , [Validators.required, Validators.pattern('^[0-9]*$')]],
    totalProdIncorrectos: [ , [Validators.required, Validators.pattern('^[0-9]*$')]],

  },
  {
    validators: [ Validator.totalProductsValidator('totalProdControlados', 'totalProdCorrectos', 'totalProdIncorrectos') ]
  });

  onKeyDown(event: KeyboardEvent) {
    const prohibitedKeys = ['-', '+', 'e', '.'];
    const allowedKeys = ['Backspace', 'Tab', 'Delete', 'ArrowRight', 'ArrowLeft', 'ArrowDown', 'ArrowUp'];
    console.log(event);
    if (!allowedKeys.includes(event.key) && (prohibitedKeys.includes(event.key) || !event.key.match(/^[0-9]+$/))) {
      console.log("PROHIBITED!");
      event.preventDefault();
    }
  }

  validateNumber(event: KeyboardEvent): void {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      event.preventDefault();  // Prevenir entrada si no es número
    }
  }

  validatePaste(event: ClipboardEvent): void {
    const pasteData = event.clipboardData?.getData('text');

    // Bloquear si el contenido pegado no es un número
    if (!/^\d+$/.test(pasteData || '')) {
      event.preventDefault();  // Prevenir pegado si no es un número
    }
  }

  goBack() {
    this.location.back();
  }

  constructor(protected activatedRoute: ActivatedRoute, 
    protected fb: FormBuilder,
    private router: Router,
    private protocolResultsService: ProtocolResultsService,
    protected notification: NotificationService,
    authContext: AuthContextService) {
      this.canModify = authContext.instant().canWrite('campaign');
      this.userAutonomousCommunity = authContext.instant().getAutonomousCommunity();
      const navigation = this.router.getCurrentNavigation();
      let state = undefined;
      if (navigation) {
        state = navigation!.extras.state as {
          campaign: any,
          resultadoSelected: any,
        }
        this.campaign = state.campaign;
        this.resultadoSelected = state.resultadoSelected;
        this.cancelRedirectPath = `../../campanas/${this.campaign.id}/resultados`;
      } else {
        this.location.back();
      }
      
    
  }

  ngOnInit(): void {
    this.campaign;
    this.updateForm1(this.campaign);
    this.loadOptions(this.campaign);
  }

  protected updateForm1(campania: CampaignForm): void {
    this.editForm1.patchValue({
      campania: campania,
    });
  }

  protected loadOptions(campania: CampaignForm): void {
    if (campania && campania.protocols) {
      this.protocolosList = campania.protocols;
    }
    if (campania && campania.participants) {
      this.caList = campania.participants;
      if (!this.canModify && this.userAutonomousCommunity) {
        this.editForm1.controls.ca.disable();
        const a = this.caList.filter(value => value.name === this.userAutonomousCommunity);
        this.editForm1.controls.ca.setValue(a[0]);
      }
    }
    if (campania && campania.campaignProductServiceDTOS) {
      this.productosList = campania.campaignProductServiceDTOS;
    }
  }

  getAutonomousCommunity(code: any): any {
    return this.caList?.find(participant => participant.id === code);
  }

  getProductService(id: any): any {
    return this.productosList?.find(producto => producto.id === id);
  }

  getProtocolo(id: any): any {
    return this.protocolosList?.find(protocolo => protocolo.id === id);
  }

  /*getSumaRespuestas(pregSi: number, pregNo: number, pregNoProcede: number): void {
    if (pregSi !== null && pregNo !== null && pregNoProcede !== null &&
    pregSi !== undefined && pregNo !== undefined && pregNoProcede !== undefined) {
      if (pregSi + pregNo + pregNoProcede !== this.totalProductosControlados) {
          this.notification.show({
        type: 'warn', // or 'danger' depending on the severity
        message: 'La suma no coincide con el total de productos controlados.',
        title: 'Error de validación'
        });
      }
    }
  }*/

  getSumaRespuestas(pregSi: number, pregNo: number, pregNoProcede: number): boolean {
    let sumaMayor = false;

    if (pregSi !== null && pregNo !== null && pregNoProcede !== null &&
      pregSi !== undefined && pregNo !== undefined && pregNoProcede !== undefined) {
      if (pregSi + pregNo + pregNoProcede !== this.totalProductosControlados) {
        sumaMayor = true;
      }
    }

    return sumaMayor;
  }

  save() {
    let respuestasInvalid = false;
    this.preguntasProtocolo.forEach((preg) => {
      if (preg.numResponseSi === undefined || preg.numResponseSi === null) {
        preg.numResponseSi = 0;
      }
      if (preg.numResponseNo === undefined || preg.numResponseNo === null) {
        preg.numResponseNo = 0;
      }
      if (preg.numResponseNoProcede === undefined || preg.numResponseNoProcede === null) {
        preg.numResponseNoProcede = 0;
      }
      respuestasInvalid = this.getSumaRespuestas(preg.numResponseSi,preg.numResponseNo,preg.numResponseNoProcede);
    });

    if (respuestasInvalid) {
      this.notification.show({ message: 'Por favor revisa las respuestas introducidas' });
    } else {
      let preguntas: TotalProtocolResults[] = [];
      let totalProtocoloResults: TotalProtocolResults;
      this.preguntasProtocolo.forEach(preg => {
          totalProtocoloResults = {
            id: undefined,
            ccaaRen: preg.numResponseNo,
            ccaaRep: preg.numResponseNoProcede,
            ccaaRes: preg.numResponseSi,
            code: null, 
            protocolResultsCode: null,
            codeQuestion: preg.orderQuestion?.toString(), // poner el numero de pregunta
            productServiceId: this.productoSelected?.id
          };
          preguntas.push(totalProtocoloResults);
      });
  
      preguntas.push(this.buildResultsTotales(this.editForm2.get('numExistentes')?.value!, this.codNumExistentes));
      preguntas.push(this.buildResultsTotales(this.editForm2.get('numControlados')?.value!, this.codNumControlados));
      preguntas.push(this.buildResultsTotales(this.editForm2.get('totalProdControlados')?.value!, this.codProdControlados));
      preguntas.push(this.buildResultsTotales(this.editForm2.get('totalProdCorrectos')?.value!, this.codProdCorrectos));
      preguntas.push(this.buildResultsTotales(this.editForm2.get('totalProdIncorrectos')?.value!, this.codProdIncorrectos));
  
  
      let protocolResults: ProtocolResults = {
        id: undefined,
        autonomousCommunityCountryCode: undefined,
        name: this.protocoloSelected?.name,
        productServiceCode: this.productoSelected?.codeProductService,
        protocolCode: this.protocoloSelected?.code,
        campaignId: this.protocoloSelected?.campaignId,
        productServiceId: this.productoSelected?.id,
        protocolId: this.protocoloSelected?.id,
        totalProtocolResultsDTOS: preguntas,
        protocolDTO: this.protocoloSelected,
        productServiceDTO: this.productoSelected,
        autonomousCommunityCountryDTO: this.caSelected
      };
  
      this.protocolResultsService.saveResults(protocolResults).subscribe((result: any) => {
        this.router.navigate([`app/campanas/${this.campaign.id}/ver`]);
      });
    }
  }

  buildResultsTotales(numTotal: number, codigo: string): TotalProtocolResults {
    let result: TotalProtocolResults;
      result = {
        id: undefined,
        ccaaRen: null,
        ccaaRep: null,
        ccaaRes: numTotal,
        code: null, 
        protocolResultsCode: null,
        codeQuestion: codigo, // poner el numero de pregunta
        productServiceId: this.productoSelected?.id
      };
      return result;
  }

  onStepChange(event: any): void {
    if (this.editForm1.get('protocolo')?.value) {
      this.protocoloSelected = this.editForm1.get('protocolo')?.value!;
    }
    if (this.editForm1.get('producto')?.value) {
      this.productoSelected = this.editForm1.get('producto')?.value!;
    }
    if (this.editForm1.get('ca')?.value) {
      this.caSelected = this.editForm1.get('ca')?.value!;
    }
    if (this.protocoloSelected) {
      if (!this.preguntasProtocolo || this.preguntasProtocolo.length == 0) {
        this.preguntasProtocolo = this.protocoloSelected.question;
      }
      
    }
    if (this.editForm2.get('totalProdControlados')?.value) {
      this.totalProductosControlados = this.editForm2.get('totalProdControlados')?.value!;
    }
  }
}
