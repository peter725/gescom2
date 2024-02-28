import { Component, ElementRef, inject, Input, ViewChild } from '@angular/core';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { CampaignForm } from '@libs/sdk/campaign';
import { Validators, ɵFormGroupRawValue, ɵGetProperty, ɵTypedOrUntyped } from '@angular/forms';
import { PhaseCampaign } from '@libs/sdk/phaseCampaign';
import { Page } from '@libs/crud-api';
import { ProtocolDetailComponent, UploadFileComponent } from '@base/pages/campaign/campaign-see-page/components';
import { DataSharingService } from '@base/services/dataSharingService';
import { Protocol } from '@libs/sdk/protocol';
import { ExcelService } from '@base/shared/utilsExcel/excel.service';
import { InfringementDialogComponent } from '@base/pages/infringement-dialog/infringement-dialog.component';
import { ProductsDialogComponent } from './products-dialog/products-dialog.component';
import { CampaignProductServiceDTO, ProductService } from '@libs/sdk/productService';
import { CampaignProductService } from '@base/shared/utilsService/campaignProduct.service';
import { NavigationExtras } from '@angular/router';
import { ProtocolResults } from '@libs/sdk/protocolResults';

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
  campaignProducts: ProductService[] | null | undefined = [];
  campaignResults: ProtocolResults[] | null | undefined = [];

  campaign: any;
  private dataSourceDialog: any;
  private dataSharingService: DataSharingService = inject(DataSharingService);
  private excelService: ExcelService = inject(ExcelService);
  private campaignProductService: CampaignProductService = inject(CampaignProductService);

  override ngOnInit(): void {
    super.ngOnInit();
    this.loadPhases();
  }

  private loadPhases(): void {
    this.crudService.findAll({ resourceName: 'phaseCampaign' })
      .subscribe({
        next: (page: Page<PhaseCampaign>) => {
          this.phases = page.content;
        },
        error: (err) => {
          console.error('Error al cargar las fases de la campaña:', err);
          // Manejar el error (mostrar mensaje al usuario, por ejemplo)
        }
      });
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
      campaignProductServiceDTOS: this.fb.control([]),
      protocolResultsDTOS: this.fb.control([])
    });
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

  protected changePhaseCampaign(){

    let actualId = this.form.get('phaseCampaign')?.value?.id;
    let newPhase;
    if (actualId! <= 14){
      actualId = actualId! + 1;
      newPhase = this.phases.find((item: any) => item.id === actualId);
    }
    this.campaign = this.form.value;
    this.campaign.phaseCampaign = newPhase;
    this.form.patchValue(this.campaign);
    this.save().then(r => console.log('changePhaseCampaign 1', this.campaign));
    console.log('changePhaseCampaign 1', this.campaign);
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


        this.campaignProductService.saveCampaignProduct(newProducts).subscribe((result: any) => {
          location.reload();
        });
      }
    });
  }

  borrarProducto(id: number): void {
    

    this.campaignProductService.delete(id).subscribe({
      next: () => {
        // Handle successful response
        
        console.log('Producto eliminado correctamente');
        location.reload();
      },
      error: (err) => {
        // Handle error
        console.error('Error al eliminar el producto:', err);
      },
      complete: () => {
        // Optional: Do something after completion (e.g., cleanup)
      }
    });
  }

  openDialog() {

    const dialogRef = this.dialog.open(UploadFileComponent,{
      width: '75%'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('El diálogo se cerró');
      this.dataSourceDialog = result;
    });
  }

  navegarAComponenteProtocol() {
    // Asumiendo que 'this.campaign' contiene los datos de la campaña actual
    this.campaign = this.form.value;
    const minimalCampaignData = {
      id: this.campaign.id,
      nameCampaign: this.campaign.nameCampaign
    };

    this.dataSharingService.changeCampaign(minimalCampaignData);
    this.router.navigate(['/app/protocol/0']);
  }

  navegarAComponenteResultados(resultado: ProtocolResults | undefined) {
    this.campaign = this.form.value;
    const navigationExtras: NavigationExtras = {
      state: {
        campaign: this.campaign,
        resultadoSelected: resultado ? resultado : undefined
      }
    };
    this.router.navigate([`app/campanas/${this.campaign.id}/resultados`], navigationExtras);
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

  navegarAComponenteIpr() {
    this.campaign = this.form.value;
    
    this.router.navigate([`app/campanas/${this.campaign.id}/ipr`]); 
  }

  openProtocolDetailDialog() {
    const dialogRef = this.dialog.open(ProtocolDetailComponent, {
      width: '600px',
      // Puedes pasar datos al diálogo así:
      data: { /* datos que quieras pasar */ }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
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


  protected readonly console = console;
}
