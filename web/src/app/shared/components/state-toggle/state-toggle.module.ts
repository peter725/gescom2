import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TranslateModule } from '@ngx-translate/core';
import { TswButtonsModule } from '../buttons';
import { StateToggleComponent } from './components';


const EXPORTED_DECLARATIONS = [
  StateToggleComponent,
];

@NgModule({
  imports: [
    CommonModule,
    TswButtonsModule,
    MatTooltipModule,
    TranslateModule,
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class StateToggleModule {
}
