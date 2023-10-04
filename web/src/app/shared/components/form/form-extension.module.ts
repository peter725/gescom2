import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { TswButtonsModule } from '@base/shared/components/buttons';
import { ConfirmationModule } from '@base/shared/confirmation';
import { ExtractValidationErrorPipe } from './extract-validation-error.pipe';
import { FormActionsComponent } from './form-actions/form-actions.component';
import { FormContainerComponent } from './form-container/form-container.component';
import { HiddenFormComponent } from './hidden-form/hidden-form.component';


const EXPORTED_DECLARATIONS = [
  // Components
  FormActionsComponent,
  FormContainerComponent,
  HiddenFormComponent,

  // Pipes
  ExtractValidationErrorPipe,
];

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ConfirmationModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    RouterModule,
    TswButtonsModule,
    TranslateModule,
    MatProgressSpinnerModule,
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class FormExtensionModule {
}
