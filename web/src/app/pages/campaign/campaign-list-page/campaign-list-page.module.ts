import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { ListPageModule } from '@base/shared/pages/list-page.module';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { StateToggleModule } from '@base/shared/components/state-toggle';
import {
  CampaignListPageRoutingModule
} from '@base/pages/campaign/campaign-list-page/campaign-list-page-routing.module';
import { CampaignListPageComponent } from '@base/pages/campaign/campaign-list-page/campaign-list-page.component';
import { CampaignListPageFilterComponent } from '@base/pages/campaign/campaign-list-page/components';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  imports: [
    CampaignListPageRoutingModule,
    CommonModule,
    ListPageModule,
    CommonsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSlideToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    StateToggleModule
  ],
  declarations: [
    CampaignListPageComponent,
    CampaignListPageFilterComponent
  ],
})
export class CampaignListPageModule {

}
