import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { TranslateModule } from '@ngx-translate/core';
import { FormExtensionModule } from '@tulsa/app/shared/components/form';
import {
  SampleDatasetColCatalogueComponent,
  SampleDatasetColComboComponent,
  SampleDatasetColComboMultiComponent,
  SampleDatasetCollectionComponent,
  SampleDatasetCollectionHeaderComponent,
  SampleDatasetColNumericComponent,
  SampleDatasetColTextComponent,
  SampleDatasetGroupComponent,
  SampleDatasetRowComponent,
} from './components';


const EXPORTED_DECLARATIONS = [
  SampleDatasetColCatalogueComponent,
  SampleDatasetColComboComponent,
  SampleDatasetColComboMultiComponent,
  SampleDatasetColNumericComponent,
  SampleDatasetColTextComponent,
  SampleDatasetCollectionComponent,
  SampleDatasetCollectionHeaderComponent,
  SampleDatasetGroupComponent,
  SampleDatasetRowComponent,
];

@NgModule({
  imports: [
    CommonModule,
    FormExtensionModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatCheckboxModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatSelectModule,
    MatMenuModule,
    ReactiveFormsModule,
    TranslateModule,
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class SampleDatasetModule {
}
