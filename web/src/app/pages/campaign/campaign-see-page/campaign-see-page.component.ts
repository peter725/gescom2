import { Component, ElementRef, inject, Input, ViewChild } from '@angular/core';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { CampaignForm } from '@libs/sdk/campaign';
import { Validators } from '@angular/forms';
import { PhaseCampaign } from '@libs/sdk/phaseCampaign';
import { Page } from '@libs/crud-api';
import { ProtocolDetailComponent, UploadFileComponent } from '@base/pages/campaign/campaign-see-page/components';
import { DataSharingService } from '@base/services/dataSharingService';
import { Protocol } from '@libs/sdk/protocol';
import { ExcelService } from '@base/shared/utilsExcel/excel.service';
import { ProductsDialogComponent } from './products-dialog/products-dialog.component';
import { CampaignProductServiceDTO, ProductService } from '@libs/sdk/productService';
import { CampaignService } from '@base/shared/utilsService/campaign.service';
import { NavigationExtras } from '@angular/router';
import { ProtocolResults } from '@libs/sdk/protocolResults';
import { PHASE_BORRADOR_RESULTADOS, PHASE_BORRADOR_RESULTADOS_DEBATE, PHASE_DATOS_INICIALES, PHASE_DOC_INSPECCION, PHASE_DOC_INSPECCION_PLAN_DEBATE, PHASE_DOC_INSPECCION_PROTOCOLO_DEBATE, PHASE_DOC_INSPECCION_PROTOCOLO_DEFINITIVO, PHASE_FICHA_TRANSPARENCIA, PHASE_IMPRESO_DEBATE, PHASE_IMPRESO_DEFINITIVO, PHASE_RESULTADOS_DEFINITIVOS, PHASE_RESULTADOS_DEFINITIVOS_PENDIENTES, PHASE_RESULTADOS_FINALES, PHASE_RESULTADOS_FINALES_DEBATE } from '@base/shared/utils/constants';
import { firstValueFrom } from 'rxjs';
import { ProtocolListPageComponent } from '@base/pages/protocol/protocol-list-page/protocol-list-page.component';
import { AuthContextService } from '@libs/security';
import { IprDetailComponent } from './components/ipr-detail-dialog/ipr-detail-dialog.component';

@Component({
  selector: 'app-campaign-see-page',
  templateUrl: './campaign-see-page.component.html',
  styleUrls: ['./campaign-see-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
  ]
})
export class CampaignSeePageComponent extends EditPageBaseComponent<any , CampaignForm>  {

  @ViewChild("fileUpload", {read: ElementRef})
  fileUpload!: ElementRef;
  @Input() requiredFileType = '';

  readonly resourceName = 'campaign';
  readonly protocolFileUpload = 'protocolFileUpload';
  protected override _createResourceTitle = 'pages.campaign.add';
  protected override _editResourceTitle = 'pages.campaign.see';

  phases: any[] = [];
  canSeeDocumentoplanificacionProtocolo = [ PHASE_DOC_INSPECCION, PHASE_DOC_INSPECCION_PROTOCOLO_DEBATE, PHASE_DOC_INSPECCION_PROTOCOLO_DEFINITIVO,
    PHASE_DOC_INSPECCION_PLAN_DEBATE, PHASE_BORRADOR_RESULTADOS, PHASE_BORRADOR_RESULTADOS_DEBATE, PHASE_IMPRESO_DEFINITIVO, PHASE_IMPRESO_DEBATE, 
    PHASE_RESULTADOS_DEFINITIVOS, PHASE_RESULTADOS_DEFINITIVOS_PENDIENTES, PHASE_RESULTADOS_FINALES, 
    PHASE_RESULTADOS_FINALES_DEBATE, PHASE_FICHA_TRANSPARENCIA ]
  canSeeResultadosCCAA = [ PHASE_RESULTADOS_DEFINITIVOS, PHASE_RESULTADOS_DEFINITIVOS_PENDIENTES, PHASE_RESULTADOS_FINALES, PHASE_RESULTADOS_FINALES_DEBATE, PHASE_FICHA_TRANSPARENCIA ]
  canSeeResultadosFinales = [ PHASE_RESULTADOS_FINALES, PHASE_RESULTADOS_FINALES_DEBATE, PHASE_FICHA_TRANSPARENCIA ]
  canSeeFichaTransparencia = [ PHASE_FICHA_TRANSPARENCIA ]
  // Nueva variable para las fases en las que se puede ver el botón de eliminar protocolo
  canSeeDeleteProtocolo = [ PHASE_DATOS_INICIALES, PHASE_DOC_INSPECCION_PROTOCOLO_DEBATE, PHASE_DOC_INSPECCION_PROTOCOLO_DEFINITIVO, PHASE_DOC_INSPECCION_PLAN_DEBATE, PHASE_DOC_INSPECCION, PHASE_BORRADOR_RESULTADOS_DEBATE, PHASE_BORRADOR_RESULTADOS, PHASE_IMPRESO_DEBATE ];
  canSeeIpr = [ PHASE_IMPRESO_DEFINITIVO, PHASE_RESULTADOS_DEFINITIVOS_PENDIENTES, PHASE_RESULTADOS_DEFINITIVOS, PHASE_RESULTADOS_FINALES_DEBATE, PHASE_RESULTADOS_FINALES, PHASE_FICHA_TRANSPARENCIA ]


  campaignProducts: ProductService[] | null | undefined = [];
  campaignResults: ProtocolResults[] | null | undefined = [];

  campaign: any;
  private dataSourceDialog: any;
  private dataSharingService: DataSharingService = inject(DataSharingService);
  private excelService: ExcelService = inject(ExcelService);
  private campaignService: CampaignService = inject(CampaignService);
  private authContextService: AuthContextService = inject(AuthContextService);
  canModify = this.authContextService.instant().canWrite('campaign');
  userAutonomousCommunity = this.removeAccents(this.authContextService.instant().getAutonomousCommunity());

  override ngOnInit(): void {
    super.ngOnInit();
    this.loadPhases();
    this.loadDocuments();
  }

  get autonomousCommunityResponsible() : string {
    return this.removeAccents(this.form.controls.autonomousCommunityResponsible.value ? this.form.controls.autonomousCommunityResponsible.value.name : "");
  }

  get autonomousCommunityParticipants() : string[] {
    return this.form.controls.participants.value ? this.form.controls.participants.value.map((p) => { return p.name }) : [];
  }

  get fullAutonomousCommunityParticipants(){
    return this.form.get('participants')?.value!;
  }

  get fullAutonomousCommunityProponents(){
    return this.form.get('proponents')?.value!;
  }

  get fullAutonomousCommunitySpecialists(){
    return this.form.get('specialists')?.value!;
  }

  removeAccents(str: string) {  // ✔ Correcto, definición de método de clase
    return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
  }

  private loadPhases(): void {
    this.crudService.findAll({ resourceName: 'phaseCampaign' })
      .subscribe({
        next: (page: Page<PhaseCampaign>) => {
          this.phases = page.content;
        },
        error: (err) => {
          // Manejar el error (mostrar mensaje al usuario, por ejemplo)
        }
      });
  }

  documents: any;
  documentsLibrary: any;
  documentsPlanification: any;
  documentsTrasparencySheet: any;
  private async loadDocuments(): Promise<void> {
    const id : any = this.route.snapshot.paramMap.get('id');
    this.documents = await firstValueFrom(this.crudService.findById(id, {
      resourceName: 'documentCampaignList',
      pathParams: { id },
    }));

    this.documentsLibrary = this.documents.content.filter((document: any) => !document.documentType || document.documentType.id === 1);
    this.documentsPlanification = this.documents.content.filter((document: any) => document.documentType && document.documentType.id === 2);
    this.documentsTrasparencySheet = this.documents.content.filter((document: any) => document.documentType && document.documentType.id === 3);
  
  }

  protected buildForm(){
    return this.fb.group<ControlsOf<CampaignForm>>({
      id: this.fb.control(null),
      year: this.fb.control(null, [Validators.required]),
      codeCpa: this.fb.control(null, [Validators.required]),
      nameCampaign: this.fb.control(null, []),
      campaignType: this.fb.control(null, [Validators.required]),
      participants: this.fb.control([], [Validators.required]),
      ambit: this.fb.control(null, [Validators.required]),
      specialists: this.fb.control([], [Validators.required]),
      proponents: this.fb.control([], [Validators.required]),
      autonomousCommunityResponsible: this.fb.control(null, [Validators.required]),
      phaseCampaign: this.fb.control(null, [Validators.required]),
      createdAt: this.fb.control(null),
      updatedAt: this.fb.control(null),
      state: this.fb.control(null),
      protocols: this.fb.control([]),
      iprDTOS: this.fb.control([]),
      campaignProductServiceDTOS: this.fb.control([]),
      protocolResultsDTOS: this.fb.control([])
    });
  }
  openProtocolEditPage(protocolId: string) {
    this.router.navigate(['app/protocol', protocolId]);
  }

  get participantsDispaly(){
    return this.form.get('participants')?.value?.map((item: any) => item.name).join(', ');
  }
  get proponentsDisplay(){
    return this.form.get('proponents')?.value?.map((item: any) => item.name).join(', ');
  }
  get specialistsDisplay(){
    return this.form.get('specialists')?.value?.map((item: any) => item.name).join(', ');
  }

  get protocolsDisplay(){
    return this.form.get('protocols')?.value!;
  }

  get iprDisplay(){
    return this.form.get('iprDTOS')?.value!;
  }

  get productsDisplay(){
    if (this.form.get('campaignProductServiceDTOS')?.value) {
      this.campaignProducts = this.form.get('campaignProductServiceDTOS')?.value;
    }

    return this.form.get('campaignProductServiceDTOS')?.value!;

  }

  get resultsDisplay(){
    if (this.form.get('protocolResultsDTOS')?.value) {
      this.campaignResults = this.form.get('protocolResultsDTOS')?.value;
    }
    return this.form.get('protocolResultsDTOS')?.value!;
  }

  //BORRADO RESULTADO DE CCAA
  async deleteResult(idResult: number) {
    try {
      await this.crudService.deleteId(idResult, {
        resourceName: 'protocolResult',
        pathParams: { id: idResult } // Pasamos el ID del documento aquí
      }).toPromise();
      location.reload();
      // this.loadDocuments();
    } catch (error) {
      console.error('Hubo un error al eliminar el resultado:', error);
    }
  }

  getAutonomousCommunityName(code: any): string {
    let name = "";
    if (this.form.get('participants')?.value) {
      this.form.get('participants')?.value?.forEach((participant) => {
        if (participant.id === code) {
          name = '[' + participant.name + ']';
        }
      });
    }
    
    return name;
  }

  getProductServiceName(id: any): string {
    let name = "";
    if (this.form.get('campaignProductServiceDTOS')?.value) {
      this.form.get('campaignProductServiceDTOS')?.value?.forEach((producto) => {
        if (producto.id === id) {
          name = producto.name;
        }
      });
    }
    return name;
  }

  getProtocolName(id: any): string {
    let name = "";
    if (this.form.get('protocols')?.value) {
      this.form.get('protocols')?.value?.forEach((protocolo) => {
        if (protocolo.id === id) {
          name = protocolo.name;
        }
      });
    }
    
    return name;
  }

  loadProductsDisplay(){
    if (this.campaignProducts?.length === 0) {
      this.campaignProducts = this.form.get('campaignProductServiceDTOS')?.value!;
    }
  }

  protected changePhaseCampaign() {
    let actualPhase = this.form.get('phaseCampaign')?.value;
    let newPhase;
    let save = true;

    if (actualPhase?.phase === PHASE_DATOS_INICIALES ) {
      newPhase = this.phases.find((item: PhaseCampaign) => item.phase === PHASE_DOC_INSPECCION);
    } else if (actualPhase?.phase === PHASE_DOC_INSPECCION || actualPhase?.phase === PHASE_DOC_INSPECCION_PROTOCOLO_DEBATE || actualPhase?.phase === PHASE_DOC_INSPECCION_PROTOCOLO_DEFINITIVO || actualPhase?.phase === PHASE_DOC_INSPECCION_PLAN_DEBATE) {
      newPhase = this.phases.find((item: PhaseCampaign) => item.phase === PHASE_BORRADOR_RESULTADOS);
    } else if (actualPhase?.phase === PHASE_BORRADOR_RESULTADOS || actualPhase?.phase === PHASE_BORRADOR_RESULTADOS_DEBATE) {
      newPhase = this.phases.find((item: PhaseCampaign) => item.phase === PHASE_IMPRESO_DEFINITIVO);
    } else if (actualPhase?.phase === PHASE_IMPRESO_DEFINITIVO || actualPhase?.phase === PHASE_IMPRESO_DEBATE) {
      newPhase = this.phases.find((item: PhaseCampaign) => item.phase === PHASE_RESULTADOS_DEFINITIVOS);
    } else if (actualPhase?.phase === PHASE_RESULTADOS_DEFINITIVOS || actualPhase?.phase === PHASE_RESULTADOS_DEFINITIVOS_PENDIENTES) {
      newPhase = this.phases.find((item: PhaseCampaign) => item.phase === PHASE_RESULTADOS_FINALES);
    } else if (actualPhase?.phase === PHASE_RESULTADOS_FINALES || actualPhase?.phase === PHASE_RESULTADOS_FINALES_DEBATE) {
      newPhase = this.phases.find((item: PhaseCampaign) => item.phase === PHASE_FICHA_TRANSPARENCIA);
    } else if (actualPhase?.phase === PHASE_FICHA_TRANSPARENCIA) {
      newPhase = this.phases.find((item: PhaseCampaign) => item.phase === PHASE_RESULTADOS_FINALES);
    } else {
      save = false;
      this.notification.show({ message: 'text.other.errorPhase' });
    }

    if (save && newPhase) {
      this.campaign = this.form.value;
      this.campaignService.saveChangePhase(this.campaign.id, newPhase).subscribe((result: any) => {
        location.reload();
      });
    }

  }


  agregarProducto(): void {
    const dialogRef = this.dialog.open(ProductsDialogComponent, {
      width: '75%',
    });

    dialogRef.afterClosed().subscribe((result: ProductService[]) => {
      if (result && result.length > 0) {
        const newProducts: CampaignProductServiceDTO[] = [];
        result.forEach(prod => {
          const newProductService: CampaignProductServiceDTO = {
            id: undefined,
            productName: prod.name,
            campaignId: this.form.get('id')?.value!,
            codeProductService: prod.code,
            productServiceId: prod.id
          };
          newProducts.push(newProductService);
        });
        this.campaignService.saveCampaignProduct(newProducts).subscribe((result: any) => {
          location.reload();
        });
      }
    });
  }

  borrarProducto(id: number): void {
    this.campaignService.deleteProduct(id).subscribe({
      next: () => {
        location.reload();
      },
      error: (err) => {
        console.error('Error al eliminar el producto:', err);
      },
      complete: () => {
        // Optional: Do something after completion (e.g., cleanup)
      }
    });
  }

  openDialogCopyProtocol() {
    const campaignId = this.form.get('id')?.value;
    this.sharedDataService.updateSharedData({ campaignId: campaignId });

    const dialogRef = this.dialog.open(ProtocolListPageComponent,{
      width: '90%',
      height: '90%',
      data: { campaignId: campaignId }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.dataSourceDialog = result;
    });
  }
  
  navegarAComponenteProtocol(protocolId: number) {

    this.campaign = this.form.value;
    const minimalCampaignData = {
      id: this.campaign.id,
      nameCampaign: this.campaign.nameCampaign
    };
    localStorage.setItem('currentCampaign', JSON.stringify(minimalCampaignData));
    this.dataSharingService.changeCampaign(minimalCampaignData);
    this.router.navigate([`/app/protocol/${protocolId}`]);
  }


  navegarAComponenteResultados(resultado: ProtocolResults | undefined) {
    this.campaign = this.form.value;
    const navigationExtras: NavigationExtras = {
      state: {
        campaign: this.campaign,
        resultadoSelected: resultado ? resultado : undefined,
        userAutonomousCommunity: this.userAutonomousCommunity
      }
    };
    this.router.navigate([`app/campanas/${this.campaign.id}/resultados`], navigationExtras);
  }

  navegarAComponenteResultadosFinales() {
    this.campaign = this.form.value;
    const navigationExtras: NavigationExtras = {
      state: {
        campaign: this.campaign
      }
    };
    this.router.navigate([`app/campanas/${this.campaign.id}/resultadosFinales`], navigationExtras);
  }

  navegarAComponenteVerResultados(resultado: ProtocolResults | undefined) {
    this.campaign = this.form.value;
    const navigationExtras: NavigationExtras = {
      state: {
        campaign: this.campaign,
        resultadoSelected: resultado ? resultado : undefined
      }
    };
    this.router.navigate([`app/campanas/${this.campaign.id}/resultados/ver`], navigationExtras);
  }

  navegarAComponenteEditarResultados(resultado: ProtocolResults | undefined) {
    this.campaign = this.form.value;
    const navigationExtras: NavigationExtras = {
      state: {
        campaign: this.campaign,
        resultadoSelected: resultado ? resultado : undefined
      }
    };
    this.router.navigate([`app/campanas/${this.campaign.id}/results/editar`], navigationExtras);
  }


  navegarAComponenteIpr() {
    this.campaign = this.form.value;
    this.router.navigate([`app/campanas/${this.campaign.id}/ipr`]); 
  }

  
  navegarAComponenteIprEdit(ipr: number) {
    this.campaign = this.form.value;
    this.router.navigate([`app/campanas/${this.campaign.id}/ipr/${ipr}`]); 
  }

  openProtocolDetailDialog() {
    const dialogRef = this.dialog.open(ProtocolDetailComponent, {
      width: '600px',
      // Puedes pasar datos al diálogo así:
      data: { /* datos que quieras pasar */ }
    });

    dialogRef.afterClosed().subscribe(result => {
      // Aquí puedes manejar datos de retorno si es necesario
    });
  }

  clickExportExcel(protocol: Protocol): void {
    this.excelService.exportExcel(protocol).subscribe(
      (res: Blob | MediaSource) => {
        const fileName = 'listado_protocolos.xlsx';
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

  verDetallesProtocol(protocol: Protocol): void {
    const campaign = this.form.value;
    const datos = {
      protocol,
      campaign,
    };
    const dialogRef = this.dialog.open(ProtocolDetailComponent, {
      width: '1000px',
      data: datos
    });
  }

  async deleteProtocol(protocol: Protocol): Promise<void> {
    await this.crudService.deleteId(protocol.id, {
      resourceName: 'protocol',
      pathParams: { id: protocol.id }
    }).toPromise();
    location.reload();
  }

  verDetallesIpr(ipr: any): void {
    const dialogRef = this.dialog.open(IprDetailComponent, {
      width: '1000px',
      data: ipr
    });
  }

  protected readonly console = console;
  protected readonly PHASE_FICHA_TRANSPARENCIA = PHASE_FICHA_TRANSPARENCIA;
  protected readonly PHASE_RESULTADOS_FINALES = PHASE_RESULTADOS_FINALES;
  protected readonly PHASE_RESULTADOS_FINALES_DEBATE = PHASE_RESULTADOS_FINALES_DEBATE;
}
