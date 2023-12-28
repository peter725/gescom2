import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CampaignAddPageRountingModule } from '@base/pages/campaign/campaign-add-page/campaign-add-page-routing.module';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { MatRadioModule } from '@angular/material/radio';
import { TabPanelModule } from '@base/shared/components/tabpanel';
import { CampaignEditPageComponent } from '@base/pages/campaign/campaign-edit-page/campaign-edit-page.component';

@NgModule({
  imports: [
    CommonModule,
    CampaignAddPageRountingModule,
    CommonsModule,
    EditPageModule,
    MatRadioModule,
    TabPanelModule,
  ],
  declarations: [
    CampaignEditPageComponent
  ],
})
export class CampaignEditPageModule {
}