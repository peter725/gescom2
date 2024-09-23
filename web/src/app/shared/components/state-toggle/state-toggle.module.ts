import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TranslateModule } from '@ngx-translate/core';
import { TswButtonsModule } from '../buttons';
import { StateToggleComponent } from './components';
import { ConfirmationModule } from '@base/shared/confirmation';


const EXPORTED_DECLARATIONS = [
  StateToggleComponent,
];

@NgModule({
  imports: [
    CommonModule,
    TswButtonsModule,
    MatTooltipModule,
    TranslateModule,
    ConfirmationModule
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class StateToggleModule {
}
