import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { TranslateModule } from '@ngx-translate/core';
import { SectionTitleComponent } from './section-header/section-title.component';
import { SectionLabelComponent } from './section-label/section-label.component';
import { SectionWrapperComponent } from './section-wrapper/section-wrapper.component';
import {MatDividerModule} from "@angular/material/divider";


const EXPORTED_DECLARATIONS = [
  SectionTitleComponent,
  SectionLabelComponent,
  SectionWrapperComponent,
];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        MatIconModule,
        MatButtonModule,
        MatDividerModule,
    ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class SectionModule {
}
