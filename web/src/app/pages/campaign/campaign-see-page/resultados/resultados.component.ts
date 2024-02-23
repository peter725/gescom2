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

  private dataSharingService: DataSharingService = inject(DataSharingService);
  name: string | null = ''; // Variable para almacenar el nombre de la campaña
  campaignId: number | null = null; // Variable para almacenar el id de la campaña
  private location: Location = inject(Location);
  campaign: CampaignForm;
  protocolosList: Protocol[] = [];
  productosList: CampaignProductServiceDTO[] = [];
  caList: AutonomousCommunity[] = [];

  protocoloSelected: Protocol | undefined;
  productoSelected: CampaignProductServiceDTO | undefined;
  caSelected: AutonomousCommunity | undefined;

  preguntasProtocolo: Question [] = [];

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
    private router: Router) {
      const navigation = this.router.getCurrentNavigation();
      const state = navigation!.extras.state as {
        campaign: any,
    }
    this.campaign = state.campaign;
  }

  ngOnInit(): void {
    this.campaign;
    console.log("Prueba");
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
    }
    if (campania && campania.campaignProductServiceDTOS) {
      this.productosList = campania.campaignProductServiceDTOS;
    }
  }

  save() {
    this.editForm1;
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
    if (this.protocoloSelected && this.preguntasProtocolo.length == 0) {
      this.preguntasProtocolo = this.protocoloSelected.Questions;
    }
  }
}
