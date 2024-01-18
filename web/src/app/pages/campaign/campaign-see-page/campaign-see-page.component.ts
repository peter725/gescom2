import { Component } from '@angular/core';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { Campaign, CampaignForm, CreateCampaign } from '@libs/sdk/campaign';
import { Validators } from '@angular/forms';
import { DocumentForm, SignFile } from '@libs/sdk/document';
import { firstValueFrom, Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-campaign-see-page',
  templateUrl: './campaign-see-page.component.html',
  styleUrls: ['./campaign-see-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
  ]
})
export class CampaignSeePageComponent extends EditPageBaseComponent<any , CampaignForm>  {

  readonly resourceName = 'campaign';
  readonly protocolFileUpload = 'protocolFileUpload';
  protected override _createResourceTitle = 'pages.campaign.add';
  protected override _editResourceTitle = 'pages.campaign.see';





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
      this.activeOperation = this.sendOperation(signFile);
      const result = await firstValueFrom(this.activeOperation) as DocumentForm;
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
    return this.crudService.create({
      file: signFile.file,
      name: signFile.file.name,
      extension: signFile.file.type,
      base64: signFile.b64
    }, {resourceName: this.protocolFileUpload});
  }

  changePhaseCampaign(){
    return this.crudService.update(this.form.get('id')?.value?), this.form.get('proponents')?.value?, this.form.get('proponents')?.value?);
    console.log('changePhaseCampaign', this.resourceId);
    console.log('changePhaseCampaign', this.resourceName);
  }

}
