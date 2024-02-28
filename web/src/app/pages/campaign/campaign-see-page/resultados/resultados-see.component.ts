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


@Component({
  selector: 'tsw-resultados-see',
  templateUrl: './resultados-see.component.html',
  styleUrls: ['./resultados.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ResultadosSeeComponent implements OnInit{

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
  campaign: CampaignForm;
  protocolosList: Protocol[] = [];
  productosList: CampaignProductServiceDTO[] = [];
  caList: AutonomousCommunity[] = [];

  protocoloSelected: any;
  productoSelected: any;
  caSelected: any;

  preguntasProtocolo: Question [] = [];

  resultadoSelected: ProtocolResults | undefined = undefined;

  editForm1 = this.fb.group({
    id: [],
    campania: this.fb.group<ControlsOf<CampaignForm>>({
      id: this.fb.control(null),
      year: this.fb.control(null),
      codeCpa: this.fb.control(null),
      nameCampaign: this.fb.control(null, []),
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
    ca: [],
    // producto: [null, [Validators.required, Validators.maxLength(50)]],
  });

  editForm2 = this.fb.group({
    numExistentes: [],
    numControlados: [],
    totalProdControlados: [],
    totalProdCorrectos: [],
    totalProdIncorrectos: [],

  });

  constructor(protected activatedRoute: ActivatedRoute, 
    protected fb: FormBuilder,
    private router: Router,
    private protocolResultsService: ProtocolResultsService) {
      const navigation = this.router.getCurrentNavigation();
      const state = navigation!.extras.state as {
        campaign: any,
        resultadoSelected: any
    }
    this.campaign = state.campaign;
    this.resultadoSelected = state.resultadoSelected;
  }

  ngOnInit(): void {
    this.campaign;
    console.log("Prueba");
    this.updateForm1(this.campaign);
    this.loadOptions(this.campaign);
    if (this.resultadoSelected) {
      this.loadResultados(this.resultadoSelected);
    }
  }

  loadResultados(resultadoSelected: ProtocolResults) {
    this.protocoloSelected = resultadoSelected?.protocolDTO;
    this.productoSelected = resultadoSelected?.productServiceDTO;
    this.caSelected = resultadoSelected?.autonomousCommunityCountryDTO;
    this.preguntasProtocolo = this.protocoloSelected?.question;

    this.editForm1.patchValue({
      protocolo: this.protocoloSelected,
      producto: this.productoSelected,
      ca: this.caSelected,
    });

    resultadoSelected.totalProtocolResultsDTOS?.forEach((protocolResult) => {
      if (protocolResult.codeQuestion === this.codNumExistentes) {
        this.editForm2.patchValue({
          numExistentes: protocolResult.ccaaRes
        });
      } else if (protocolResult.codeQuestion === this.codNumControlados) {
        this.editForm2.patchValue({
          numControlados: protocolResult.ccaaRes
        });
      } else if (protocolResult.codeQuestion === this.codProdControlados) {
        this.editForm2.patchValue({
          totalProdControlados: protocolResult.ccaaRes
        });
      } else if (protocolResult.codeQuestion === this.codProdCorrectos) {
        this.editForm2.patchValue({
          totalProdCorrectos: protocolResult.ccaaRes
        });
      } else if (protocolResult.codeQuestion === this.codProdIncorrectos) {
        this.editForm2.patchValue({
          totalProdIncorrectos: protocolResult.ccaaRes
        });
      } else if (this.preguntasProtocolo && this.preguntasProtocolo.length > 0) {
       
        this.preguntasProtocolo.forEach((question) => {
          if (question.orderQuestion === protocolResult.codeQuestion) {
            // Asignar el resultado a la pregunta
            question.numResponseSi = protocolResult.ccaaRes;
            question.numResponseNo = protocolResult.ccaaRen;
            question.numResponseNoProcede = protocolResult.ccaaRep;
          }
        });
      }
    });
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

  save() {

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

    preguntas.push(this.buildResultsTotales(this.editForm1.get('numExistentes')?.value!, this.codNumExistentes));
    preguntas.push(this.buildResultsTotales(this.editForm1.get('numControlados')?.value!, this.codNumControlados));
    preguntas.push(this.buildResultsTotales(this.editForm1.get('totalProdControlados')?.value!, this.codProdControlados));
    preguntas.push(this.buildResultsTotales(this.editForm1.get('totalProdCorrectos')?.value!, this.codProdCorrectos));
    preguntas.push(this.buildResultsTotales(this.editForm1.get('totalProdIncorrectos')?.value!, this.codProdIncorrectos));


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
      location.reload();
    });

    this.editForm1;
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

}
