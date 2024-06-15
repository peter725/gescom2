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
      id: this.fb.control(null),
      year: this.fb.control({ value: null, disabled: true }),
      codeCpa: this.fb.control(null),
      nameCampaign: this.fb.control({ value: null, disabled: true }, []),
      campaignType: this.fb.control(null),
      participants: this.fb.control([]),
      ambit: this.fb.control(null),
      specialists: this.fb.control([]),
      proponents: this.fb.control([]),
      autonomousCommunityResponsible: this.fb.control(null),
      phaseCampaign: this.fb.control(null),
      createdAt: this.fb.control(null),
      updatedAt: this.fb.control(null),
      state: this.fb.control(null),
      protocols: this.fb.control([]),
      campaignProductServiceDTOS: this.fb.control([]),
    }),
    protocolo: [],
    producto: [],
    ca: [] as AutonomousCommunity[],
    // producto: [null, [Validators.required, Validators.maxLength(50)]],
  });

  editForm2 = this.fb.group({
    numExistentes: [],
    numControlados: [],
    totalProdControlados: [],
    totalProdCorrectos: [],
    totalProdIncorrectos: [],

  },
  {
    validators: [ Validator.totalProductsValidator('totalProdControlados', 'totalProdCorrectos', 'totalProdIncorrectos') ]
  });

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
      if (preg.response === 'S' && preg.numResponseSi !== null && preg.numResponseNo !== null && preg.numResponseNoProcede !== null &&
        preg.numResponseSi !== undefined && preg.numResponseNo !== undefined && preg.numResponseNoProcede !== undefined) {
        if (preg.numResponseSi + preg.numResponseNo + preg.numResponseNoProcede !== this.totalProductosControlados) {
          respuestasInvalid = true;
        }
      }
    });

    if (respuestasInvalid) {
      this.notification.show({ message: 'text.other.pleaseReview' });
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
