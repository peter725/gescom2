import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { ProtocolEditPageRoutingModule } from './protocol-edit-page-routing.module';
import { ProtocolEditPageComponent } from './protocol-edit-page.component';
import {MatChipsModule} from "@angular/material/chips";
import { InfringementDialogComponent } from '@base/pages/infringement-dialog/infringement-dialog.component';


@NgModule({
  imports: [
    CommonModule,
    ProtocolEditPageRoutingModule,
    CommonsModule,
    EditPageModule,
    MatChipsModule,
    InfringementDialogComponent
  ],
  declarations: [
    ProtocolEditPageComponent

  ],
})
export class ProtocolEditPageModule {
}
