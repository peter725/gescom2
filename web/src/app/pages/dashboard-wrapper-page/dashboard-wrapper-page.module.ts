import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatMenuModule} from '@angular/material/menu';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {TranslateModule} from '@ngx-translate/core';
import {FormExtensionModule} from '@base/shared/components/form';
import {SidebarModule} from '@base/shared/components/sidebar';
import {LogosModule} from '@base/shared/gui/logos';
import { CommonsModule } from '@base/shared/pages/commons.module';
import {TswSelectModule} from '@base/shared/select';
import {
  FooterComponent,
  HeaderComponent,
  LazyModuleLoadErrorComponent,
  LazyModuleLoadingComponent
} from './components';
import {DashboardWrapperPageRoutingModule} from './dashboard-wrapper-page-routing.module';
import {DashboardWrapperPageComponent} from './dashboard-wrapper-page.component';
import {TswIconModule} from "@base/shared/components/icon";


@NgModule({
  imports: [
    CommonModule,
    DashboardWrapperPageRoutingModule,
    CommonsModule,
    SidebarModule,
    MatProgressSpinnerModule,
    MatCardModule,
    LogosModule,
    MatMenuModule,
    TranslateModule,
    TswSelectModule,
    FormExtensionModule,
    TswIconModule,
  ],
  declarations: [
    DashboardWrapperPageComponent,
    FooterComponent,
    HeaderComponent,
    LazyModuleLoadErrorComponent,
    LazyModuleLoadingComponent,
  ],
})
export class DashboardWrapperPageModule {
}
