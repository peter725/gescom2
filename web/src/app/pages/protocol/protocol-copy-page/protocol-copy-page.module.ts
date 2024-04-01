import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { ProtocolCopyPageRoutingModule } from './protocol-copy-page-routing.module';
import { ProtocolCopyPageComponent } from './protocol-copy-page.component';
import {MatChipsModule} from "@angular/material/chips";
import { InfringementDialogComponent } from '@base/pages/infringement-dialog/infringement-dialog.component';


@NgModule({
  imports: [
    CommonModule,
    ProtocolCopyPageRoutingModule,
    CommonsModule,
    EditPageModule,
    MatChipsModule,
    InfringementDialogComponent
  ],
  declarations: [
    ProtocolCopyPageComponent

  ],
})
export class ProtocolCopyPageModule {
}
