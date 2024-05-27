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
import { IprDTO, ResultsResponseDTO } from '@libs/sdk/ipr';
import { ExcelService } from '@base/shared/utilsExcel/excel.service';


@Component({
  selector: 'tsw-resultados-finales',
  templateUrl: './resultados-finales.component.html',
  styleUrls: ['./resultados-finales.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ResultadosFinalesComponent implements OnInit{

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
  iprList: IprDTO[] = []; 
  totalProductosControlados: any;

  protocoloSelected: any;
  productoSelected: any;
  iprSelected: any;

  preguntasProtocolo: Question [] = [];
  preguntasIpr: any;
  protocoloResultados: ResultsResponseDTO[] = [];

  resultadoSelected: ProtocolResults | undefined = undefined;
  iprResultadosProtocoloName = 'Resultados segun plantilla de protocolo';

  iprResultadosProtocolo: IprDTO = {
    id: 0,
    code: '',
    name: this.iprResultadosProtocoloName,
    product: '',
    campaignId: 0,
    protocolCode: '',
    protocolId: 0,
    nameCampaign: '',
    iprQuestionDTOList: []
  };

  isTablaProtocolo: boolean = false; // Para mostrar la tabla de resultados por protocolo o la tabla de ipr

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
    ipr: []
    // producto: [null, [Validators.required, Validators.maxLength(50)]],
  });

  constructor(protected activatedRoute: ActivatedRoute, 
    protected fb: FormBuilder,
    private router: Router,
    private protocolResultsService: ProtocolResultsService,
    private excelService: ExcelService,
    protected notification: NotificationService) {
      const navigation = this.router.getCurrentNavigation();
      let state = undefined;
      if (navigation) {
        state = navigation!.extras.state as {
        campaign: any,
        resultadoSelected: any
        }
        this.campaign = state.campaign;
        this.resultadoSelected = state.resultadoSelected;
        this.productoSelected = state.campaign.productoSelected;
        this.cancelRedirectPath = `../../campanas/${this.campaign.id}/resultados`;
      } else {
        this.goBack();
      }
      
    
  }

  ngOnInit(): void {
    this.campaign;
    console.log("Prueba");
    this.updateForm1(this.campaign);
    this.loadOptions(this.campaign);
  }

  goBack() {
    this.location.back();
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
    if (campania && campania.campaignProductServiceDTOS) {
      this.productosList = campania.campaignProductServiceDTOS;
    }

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

  protocolOnChange() {
    if (this.editForm1.get('protocolo')?.value) {
      this.protocoloSelected = this.editForm1.get('protocolo')?.value!;
      this.preguntasIpr = this.protocoloSelected.resultsResponseDTO?.questionsResponseDTOS;
      console.log(this.preguntasIpr);
      this.iprList = this.protocoloSelected.iprDTOS;
      this.protocoloResultados = this.protocoloSelected.resultsResponseDTO;
    }
    console.log('protocoloResultados', this.protocoloResultados)
  }

  iprOnChange() {
    if (this.editForm1.get('ipr')?.value) {
      this.iprSelected = this.editForm1.get('ipr')?.value!;
      this.preguntasIpr = this.iprSelected.resultsResponseDTO?.questionsResponseDTOS
      this.isTablaProtocolo = this.iprSelected == this.iprResultadosProtocolo ? true : false;
      this.sortQuestionsByOrder();
    }
  }

  sortQuestionsByOrder() {
    if (this.preguntasProtocolo) {
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
    if (this.preguntasIpr) {
      this.preguntasIpr.sort((a: { orderQuestion: number | null; }, b: { orderQuestion: number | null; }) => {
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
  }

  exportExcel(): void {

    if (this.isTablaProtocolo) {
      this.excelService.exportExcelResultadosProtocolo(this.protocoloSelected).subscribe(
        (res: Blob | MediaSource) => {
          const fileName = 'resultados_finales.xlsx';
          const objectUrl = URL.createObjectURL(res);
          const a: HTMLAnchorElement = document.createElement('a');
  
          a.href = objectUrl;
          a.download = fileName;
          document.body.appendChild(a);
          a.click();
  
          document.body.removeChild(a);
          URL.revokeObjectURL(objectUrl);
        },
        (error: any) => {
          console.log('Error download {}', error);
        }
      );
    } else {
        this.excelService.exportExcelResultadosIpr(this.iprSelected).subscribe(
          (res: Blob | MediaSource) => {
            const fileName = 'resultados_finales.xlsx';
            const objectUrl = URL.createObjectURL(res);
            const a: HTMLAnchorElement = document.createElement('a');
    
            a.href = objectUrl;
            a.download = fileName;
            document.body.appendChild(a);
            a.click();
    
            document.body.removeChild(a);
            URL.revokeObjectURL(objectUrl);
          },
          (error: any) => {
            console.log('Error download {}', error);
          }
        );
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
    if (this.protocoloSelected) {
      if (!this.preguntasProtocolo || this.preguntasProtocolo.length == 0) {
        this.preguntasProtocolo = this.protocoloSelected.resultsResponseDTO.questionsResponseDTOS;
      }
      
    }

  }
}
