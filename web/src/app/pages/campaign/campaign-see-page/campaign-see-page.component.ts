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

  campaign: any;
  private dataSourceDialog: any;
  private dataSharingService: DataSharingService = inject(DataSharingService);


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
    console.log('navegarAComponenteProtocol', minimalCampaignData);
    this.router.navigate(['/app/protocol/0']);
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
}
