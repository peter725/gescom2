import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { TranslateModule } from '@ngx-translate/core';
import { ButtonComponent } from './button/button.component';
import { RaisedButtonComponent } from './raised-button/raised-button.component';
import { StrokedButtonComponent } from './stroked-button/stroked-button.component';

const EXPORTED_DECLARATIONS = [
  ButtonComponent,
  RaisedButtonComponent,
  StrokedButtonComponent,
];

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    TranslateModule,
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: [
    MatButtonModule,
    ...EXPORTED_DECLARATIONS
  ],
})
export class TswButtonsModule {
}
