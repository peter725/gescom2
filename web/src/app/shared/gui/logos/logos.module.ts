import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';
import { LogoComponent } from './logo/logo.component';


const EXPORTED_DECLARATIONS = [
  LogoComponent,
];

@NgModule({
  imports: [
    CommonModule,
    MatTooltipModule,
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class LogosModule { }
