import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { FormExtensionModule } from '@base/shared/components/form';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { LogoutPageRoutingModule } from './logout-page-routing.module';
import { LogoutPageComponent } from './logout-page.component';


@NgModule({
  declarations: [LogoutPageComponent],
  imports: [
    CommonModule,
    LogoutPageRoutingModule,
    CommonsModule,
    FormExtensionModule,
    MatProgressSpinnerModule,
  ],
})
export class LogoutPageModule {
}
