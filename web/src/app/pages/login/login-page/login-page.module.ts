import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { FormExtensionModule } from '@base/shared/components/form';
import { LogosModule } from '@base/shared/gui/logos';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { LoginRequestComponent, LoginStatusComponent } from './components';
import { LoginPageRoutingModule } from './login-page-routing.module';
import { LoginPageComponent } from './login-page.component';


@NgModule({
  imports: [
    CommonModule,
    LoginPageRoutingModule,
    CommonsModule,
    MatCardModule,
    LogosModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    FormExtensionModule,
    MatProgressSpinnerModule,
  ],
  declarations: [
    LoginPageComponent,
    LoginRequestComponent,
    LoginStatusComponent,
  ],
})
export class LoginPageModule {
}
