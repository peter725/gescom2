import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { TranslateModule } from '@ngx-translate/core';
import { NotificationDialogComponent } from './notification-dialog.component';


@NgModule({
  imports: [
    CommonModule,
    MatDialogModule,
    MatIconModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    TranslateModule,
  ],
  declarations: [
    NotificationDialogComponent,
  ],
  exports: [],
})
export class NotificationModule {
  public static forRoot(): ModuleWithProviders<NotificationModule> {
    return {
      ngModule: NotificationModule,
      providers: [],
    };
  }
}
