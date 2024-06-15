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
  campaign: any;
  protocolosList: Protocol[] = [];
  productosList: CampaignProductServiceDTO[] = [];
  caList: AutonomousCommunity[] = [];

  protocoloSelected: any;
  productoSelected: any;
  caSelected: any;

  preguntasProtocolo: Question [] = [];

  resultadoSelected: ProtocolResults | undefined = undefined;

  numExistentes: any;
  numControlados: any;
  totalProdControlados: any;
  totalProdCorrectos: any;
  totalProdIncorrectos: any;

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

  constructor(protected activatedRoute: ActivatedRoute, 
    protected fb: FormBuilder,
    private router: Router) {
      const navigation = this.router.getCurrentNavigation();

      let state = undefined;
      if (navigation) {
        state = navigation!.extras.state as {
        campaign: any,
        resultadoSelected: any
        }
        this.campaign = state.campaign;
        this.resultadoSelected = state.resultadoSelected;
        console.log('componente ver, resultadoSelected.protocolDTO', this.resultadoSelected?.protocolDTO)
      } else {
        this.router.navigate([`app/campanas/consulta`]);
      }
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
    this.sortQuestionsByOrder();

    this.editForm1.patchValue({
      protocolo: this.protocoloSelected,
      producto: this.productoSelected,
      ca: this.caSelected,
    });

    resultadoSelected.totalProtocolResultsDTOS?.forEach((protocolResult) => {

      if (protocolResult.codeQuestion === this.codNumExistentes) {
          this.numExistentes = protocolResult.ccaaRes
      } else if (protocolResult.codeQuestion === this.codNumControlados) {
          this.numControlados = protocolResult.ccaaRes
      } else if (protocolResult.codeQuestion === this.codProdControlados) {
          this.totalProdControlados = protocolResult.ccaaRes
      } else if (protocolResult.codeQuestion === this.codProdCorrectos) {
          this.totalProdCorrectos = protocolResult.ccaaRes
      } else if (protocolResult.codeQuestion === this.codProdIncorrectos) {
          this.totalProdIncorrectos = protocolResult.ccaaRes
      } else if (this.preguntasProtocolo && this.preguntasProtocolo.length > 0) {
        console.log('numResponseSi',protocolResult.ccaaRes)
       
        this.preguntasProtocolo.forEach((question) => {
          if (question.orderQuestion) {
            if (question.orderQuestion.toString() === protocolResult.codeQuestion) {
              // Asignar el resultado a la pregunta
              question.numResponseSi = protocolResult.ccaaRes;
              question.numResponseNo = protocolResult.ccaaRen;
              question.numResponseNoProcede = protocolResult.ccaaRep;
            }
          }
        });
      }
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
    console.log('revisando el id de campaña en ver resultados', campania.id)
  }

  navegarAComponente(): void {
    // Reemplaza 'ruta/deseada' con la ruta a la que quieres navegar
    this.router.navigate([`/app/campanas/${this.campaign.id}/ver`]);
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

}
