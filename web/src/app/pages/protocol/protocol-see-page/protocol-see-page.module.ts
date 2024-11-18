import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { ProtocolSeePageRoutingModule } from './protocol-see-page-routing.module';
import { ProtocolSeePageComponent } from './protocol-see-page.component';
import {MatChipsModule} from "@angular/material/chips";
import { InfringementDialogComponent } from '@base/pages/infringement-dialog/infringement-dialog.component';


@NgModule({
  imports: [
    CommonModule,
    ProtocolSeePageRoutingModule,
    CommonsModule,
    EditPageModule,
    MatChipsModule,
    InfringementDialogComponent
  ],
  declarations: [
    ProtocolSeePageComponent
  ],
})
export class ProtocolSeePageModule {
}
