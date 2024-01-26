import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { Campaign, CampaignForm, CreateCampaign } from '@libs/sdk/campaign';
import { Validators } from '@angular/forms';
import { DocumentForm, SignFile } from '@libs/sdk/document';
import { firstValueFrom, Observable, Subscription } from 'rxjs';
import { PhaseCampaign } from '@libs/sdk/phaseCampaign';
import { Page } from '@libs/crud-api';

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

  async onUpload(signFile: SignFile) {
    try {
      console.log('onUpload 0', signFile);
      this.activeOperation = this.sendOperation(signFile);
      const result = await firstValueFrom(this.activeOperation) as DocumentForm;
      console.log('onUpload 2', result);
      this.activeOperation = undefined;
      signFile.form?.patchValue({'fileName': result.name, id: result.id});
      this.status.status = 'IDLE';
      this.notification.show({message: 'text.other.dataSaved'});
    } catch (e: any) {
      await this.afterSaveError(e);
      signFile.form?.patchValue({'fileName': "", id: null});
    }

  }

  private sendOperation(signFile: SignFile): Observable<any> {
    console.log('sendOperation 0', signFile);
    console.log('sendOperation 1', signFile.documentType);
    console.log('sendOperation 2', signFile.file);
    console.log('sendOperation 3',  this.form.get('id')?.value);
    return this.crudService.create({
      file: signFile.file,
      name: signFile.file.name,
      extension: signFile.file.type,
      base64: signFile.b64,
      campaignId: this.form.get('id')?.value,
      documentType: signFile.documentType,
    }, {resourceName: this.protocolFileUpload});
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

  redirectToProtocoAdd(campaignId: string | undefined) {
    this.router.navigate(['protocolManagementCreate', campaignId]);
  }


}
