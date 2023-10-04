import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { ConfirmActionDirective } from './confirm-action.directive';
import { ConfirmDialogComponent } from './confirm-dialog.component';


@NgModule({
  imports: [
    CommonModule,
    MatDialogModule,
    MatIconModule,
    MatButtonModule,
  ],
  declarations: [
    ConfirmActionDirective,
    ConfirmDialogComponent,
  ],
  exports: [
    ConfirmActionDirective,
  ]
})
export class ConfirmationModule {
  // Just a convenience to remember that this module requires to be
  // imported in AppModule first.
  // It also permits future config options.
  public static forRoot(): ModuleWithProviders<ConfirmationModule> {
    return {
      ngModule: ConfirmationModule,
      providers: [
        // Enable this if manual instance control is required and
        // remove existing provide in root from the service.
        // { provide: ConfirmationService, deps: [MatDialog] },
      ],
    };
  }
}
