import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { ListPageModule } from '@base/shared/pages/list-page.module';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { StateToggleModule } from '@base/shared/components/state-toggle';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {
  ProtocolListPageFilterComponent
} from '@base/pages/campaign/campaign-see-page/components/protocol/protocol-list-page/components';
import {
  ProtocolListPageComponent
} from '@base/pages/campaign/campaign-see-page/components/protocol/protocol-list-page/protocol-list-page.component';
import {
  ProtocolListPageRoutingModule
} from '@base/pages/campaign/campaign-see-page/components/protocol/protocol-list-page/protocol-list-page-routing.module';

@NgModule({
  imports: [
    ProtocolListPageRoutingModule,
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
    ProtocolListPageComponent,
    ProtocolListPageFilterComponent
  ],
})
export class ProtocolListPageModule {

}
