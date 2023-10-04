import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { IconComponent } from './icon.component';


@NgModule({
  imports: [
    CommonModule,
    MatIconModule,
  ],
  declarations: [
    IconComponent
  ],
  exports: [
    IconComponent,
    MatIconModule,
  ]
})
export class TswIconModule {
}
