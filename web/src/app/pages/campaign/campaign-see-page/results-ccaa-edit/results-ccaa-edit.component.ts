import { Component, inject, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { Protocol, Question } from '@libs/sdk/protocol';
import { DataSharingService } from '@base/services/dataSharingService';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { CampaignForm } from '@libs/sdk/campaign';
import { CampaignProductServiceDTO } from '@libs/sdk/productService';
import { ProtocolResults, TotalProtocolResults } from '@libs/sdk/protocolResults';
import { ProtocolResultsService } from '@base/shared/utilsService/protocolResults.service';
import { NotificationService } from '@base/shared/notification';
import { IprDTO, ResultsResponseDTO } from '@libs/sdk/ipr';
import { ExcelService } from '@base/shared/utilsExcel/excel.service';
import { AutonomousCommunity } from '@libs/sdk/autonomousCommunity';

@Component({
  selector: 'tsw-resultados-edit',
  templateUrl: './results-ccaa-edit.component.html',
  styleUrls: ['./results-ccaa-edit.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ResultsCcaaEditComponent implements OnInit{

  readonly resourceName = 'protocol';
  cancelRedirectPath = 'app/campanas/consulta';

  codNumExistentes: string = 'DC1';
  codNumControlados: string = 'DC8';
  codProdControlados: string = 'DC9';
  codProdCorrectos: string = 'DC10';
  codProdIncorrectos: string = 'DC11';

  private dataSharingService: DataSharingService = inject(DataSharingService);
  private protocolResultsService: ProtocolResultsService = inject(ProtocolResultsService);
  name: string | null = ''; // Variable para almacenar el nombre de la campaña
  campaignId: number | null = null; // Variable para almacenar el id de la campaña
  private location: Location = inject(Location);
  campaign: any;
  protocolosList: Protocol[] = [];
  productosList: CampaignProductServiceDTO[] = [];
  caList: AutonomousCommunity[] = [];

  protocoloSelected: any;
  productoSelected: any;
  caSelected: any;

  preguntasProtocolo: Question[] = [];

  resultadoSelected: ProtocolResults | undefined = undefined;

  numExistentes: any;
  numControlados: any;
  totalProdControlados: any;
  totalProdCorrectos: any;
  totalProdIncorrectos: any;

  editForm1: FormGroup = this.fb.group({
    id: [],
    campania: this.fb.group({
      id: this.fb.control<number | null>(null),
      year: this.fb.control<number | null>(null),
      codeCpa: this.fb.control<string | null>(null),
      nameCampaign: this.fb.control<string | null>(null),
      campaignType: this.fb.control<string | null>(null),
      participants: this.fb.control<any[]>([]),
      ambit: this.fb.control<string | null>(null),
      specialists: this.fb.control<any[]>([]),
      proponents: this.fb.control<any[]>([]),
      autonomousCommunityResponsible: this.fb.control<string | null>(null),
      phaseCampaign: this.fb.control<string | null>(null),
      createdAt: this.fb.control<string | null>(null),
      updatedAt: this.fb.control<string | null>(null),
      state: this.fb.control<string | null>(null),
      protocols: this.fb.control<any[]>([]),
      campaignProductServiceDTOS: this.fb.control<any[]>([]),
    }),
    protocolo: [],
    producto: [],
    ca: [],
    numExistentes: this.fb.control<number | null>(null),
    numControlados: this.fb.control<number | null>(null),
    totalProdControlados: this.fb.control<number | null>(null),
    totalProdCorrectos: this.fb.control<number | null>(null),
    totalProdIncorrectos: this.fb.control<number | null>(null),
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder,
    private router: Router,
    protected notification: NotificationService,
  ) {
    const navigation = this.router.getCurrentNavigation();
    let state = undefined;
    if (navigation) {
      state = navigation!.extras.state as {
        campaign: any,
        resultadoSelected: any
      }
      this.campaign = state.campaign;
      this.resultadoSelected = state.resultadoSelected;
    } else {
      this.router.navigate([`app/campanas/consulta`]);
    }
    console.log('Este es el componente de resultados finales, pero aquí llega para ver resultados de cada CCAA');
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
    console.log('resultados seleecionados:' ,resultadoSelected.totalProtocolResultsDTOS)
    this.protocoloSelected = resultadoSelected?.protocolDTO;
    this.productoSelected = resultadoSelected?.productServiceDTO;
    this.caSelected = resultadoSelected?.autonomousCommunityCountryDTO;
    this.preguntasProtocolo = this.protocoloSelected?.question;
    console.log('Preguntas antes de forEach', this.preguntasProtocolo)
    this.sortQuestionsByOrder();

    this.numExistentes = resultadoSelected.totalProtocolResultsDTOS?.find(r => r.codeQuestion === this.codNumExistentes)?.ccaaRes || null;
    this.numControlados = resultadoSelected.totalProtocolResultsDTOS?.find(r => r.codeQuestion === this.codNumControlados)?.ccaaRes || null;
    this.totalProdControlados = resultadoSelected.totalProtocolResultsDTOS?.find(r => r.codeQuestion === this.codProdControlados)?.ccaaRes || null;
    this.totalProdCorrectos = resultadoSelected.totalProtocolResultsDTOS?.find(r => r.codeQuestion === this.codProdCorrectos)?.ccaaRes || null;
    this.totalProdIncorrectos = resultadoSelected.totalProtocolResultsDTOS?.find(r => r.codeQuestion === this.codProdIncorrectos)?.ccaaRes || null;

    this.preguntasProtocolo.forEach((question, index) => {
      console.log('Pregunstas',question);
      const numericCodeQuestion = Number(resultadoSelected.totalProtocolResultsDTOS?.find(r => r.codeQuestion))
      const resultado = resultadoSelected.totalProtocolResultsDTOS?.find(r => Number(r.codeQuestion) === question.orderQuestion);
      console.log('Resultado: ', resultado)
      if (resultado) {
        question.numResponseSi = resultado.ccaaRes;
        question.numResponseNo = resultado.ccaaRen;
        question.numResponseNoProcede = resultado.ccaaRep;
      }
    });

    console.log('Preguntas despues de forEach', this.preguntasProtocolo)

    // Asignar el ID correcto de `protocolResults`
    this.editForm1.patchValue({
      id: resultadoSelected.id,
      campania: this.campaign,
      protocolo: this.protocoloSelected,
      producto: this.productoSelected,
      ca: this.caSelected,
      numExistentes: this.numExistentes,
      numControlados: this.numControlados,
      totalProdControlados: this.totalProdControlados,
      totalProdCorrectos: this.totalProdCorrectos,
      totalProdIncorrectos: this.totalProdIncorrectos,
    });
  }




  sortQuestionsByOrder() {
    this.preguntasProtocolo.sort((a, b) => {
      if (a.orderQuestion === null && b.orderQuestion === null) {
        return 0; // Both null, keep order as is
      } else if (a.orderQuestion === null) {
        return 1; // a is null, put it after b
      } else if (b.orderQuestion === null) {
        return -1; // b is null, put it before a
      } else {
        return a.orderQuestion - b.orderQuestion; // Sort by orderQuestion
      }
    });
  }

  protected updateForm1(campania: CampaignForm): void {
    this.editForm1.patchValue({
      id: campania.id,
      campania: {
        id: campania.id,
        year: campania.year,
        codeCpa: campania.codeCpa,
        nameCampaign: campania.nameCampaign,
        campaignType: campania.campaignType,
        participants: campania.participants,
        ambit: campania.ambit,
        specialists: campania.specialists,
        proponents: campania.proponents,
        autonomousCommunityResponsible: campania.autonomousCommunityResponsible,
        phaseCampaign: campania.phaseCampaign,
        createdAt: campania.createdAt,
        updatedAt: campania.updatedAt,
        state: campania.state,
        protocols: campania.protocols,
        campaignProductServiceDTOS: campania.campaignProductServiceDTOS
      },
      protocolo: this.protocoloSelected,
      producto: this.productoSelected,
      ca: this.caSelected,
      numExistentes: this.numExistentes,
      numControlados: this.numControlados,
      totalProdControlados: this.totalProdControlados,
      totalProdCorrectos: this.totalProdCorrectos,
      totalProdIncorrectos: this.totalProdIncorrectos,
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

  goBack() {
    this.location.back();
  }

  saveResults(): void {
    if (this.editForm1.valid) {
      const resultsToSave = {
        id: this.editForm1.get('id')?.value,
        name: this.campaign.nameCampaign,
        autonomousCommunityCountryId: this.caSelected?.id,
        autonomousCommunityCountryCode: this.caSelected?.code,
        autonomousCommunityCountryDTO: this.caSelected,
        productServiceId: this.productoSelected?.id,
        productServiceCode: this.productoSelected?.code,
        productServiceDTO: this.productoSelected,
        campaignId: this.campaign.id,
        protocolId: this.protocoloSelected?.id,
        protocolCode: this.protocoloSelected?.code,
        protocolDTO: this.protocoloSelected,
        protocolName: this.protocoloSelected?.name,
        code: this.protocoloSelected?.code,
        totalProtocolResultsDTOS: [
          ...this.preguntasProtocolo.map((pregunta: any) => ({
            id: pregunta.id,
            ccaaRen: pregunta.numResponseNo,
            ccaaRep: pregunta.numResponseNoProcede,
            ccaaRes: pregunta.numResponseSi,
            code: pregunta.code,
            protocolResultsCode: this.protocoloSelected?.code,
            codeQuestion: pregunta.orderQuestion.toString(),
            protocolResultsId: this.editForm1.get('id')?.value
          })),
          // Agregar los campos adicionales a totalProtocolResultsDTOS
          {
            id: null,
            ccaaRen: null,
            ccaaRep: null,
            ccaaRes: this.editForm1.get('numExistentes')?.value,
            code: this.codNumExistentes,
            protocolResultsCode: this.protocoloSelected?.code,
            codeQuestion: this.codNumExistentes,
            protocolResultsId: this.editForm1.get('id')?.value
          },
          {
            id: null,
            ccaaRen: null,
            ccaaRep: null,
            ccaaRes: this.editForm1.get('numControlados')?.value,
            code: this.codNumControlados,
            protocolResultsCode: this.protocoloSelected?.code,
            codeQuestion: this.codNumControlados,
            protocolResultsId: this.editForm1.get('id')?.value
          },
          {
            id: null,
            ccaaRen: null,
            ccaaRep: null,
            ccaaRes: this.editForm1.get('totalProdControlados')?.value,
            code: this.codProdControlados,
            protocolResultsCode: this.protocoloSelected?.code,
            codeQuestion: this.codProdControlados,
            protocolResultsId: this.editForm1.get('id')?.value
          },
          {
            id: null,
            ccaaRen: null,
            ccaaRep: null,
            ccaaRes: this.editForm1.get('totalProdCorrectos')?.value,
            code: this.codProdCorrectos,
            protocolResultsCode: this.protocoloSelected?.code,
            codeQuestion: this.codProdCorrectos,
            protocolResultsId: this.editForm1.get('id')?.value
          },
          {
            id: null,
            ccaaRen: null,
            ccaaRep: null,
            ccaaRes: this.editForm1.get('totalProdIncorrectos')?.value,
            code: this.codProdIncorrectos,
            protocolResultsCode: this.protocoloSelected?.code,
            codeQuestion: this.codProdIncorrectos,
            protocolResultsId: this.editForm1.get('id')?.value
          }
        ]
      };

      console.log('Resultados a guardar:', resultsToSave);
      this.protocolResultsService.updateResults(resultsToSave).subscribe(
        (response) => {
          console.log('Resultados guardados con éxito:', response);
          this.notification.show({
            title: 'text.other.dataSaved',
            message: 'Datos guardados exitosamente',
          });
          // Redirigir a la página de la campaña específica que se acaba de editar
          this.router.navigate([`/app/campanas/${this.campaign.id}/ver`]); // Ajusta la ruta según sea necesario
        },
        (error) => {
          console.error('Error al guardar los resultados:', error);
        }
      );
    } else {
      console.log('Formulario inválido');
    }
  }



}