import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { TranslateModule } from '@ngx-translate/core';
import { ExtractModelPathModule } from '@libs/utils/extract-model-path';
import { CustomSelectComponent } from './custom-select/custom-select.component';
import { CustomAutocompleteComponent } from './custom-autocomplete/custom-autocomplete.component';


const EXPORTED_DECLARATIONS = [
  CustomSelectComponent,
  CustomAutocompleteComponent,
];

@NgModule({
  imports: [
    CommonModule,
    ExtractModelPathModule,
    MatSelectModule,
    TranslateModule,
    MatAutocompleteModule,
    MatInputModule,
    ExtractModelPathModule,
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class TswSelectModule {
}
