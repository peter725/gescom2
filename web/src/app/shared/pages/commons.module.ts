import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TranslateModule } from '@ngx-translate/core';
import { BreadcrumbsModule } from '@base/shared/components/breadcrumbs';
import { TswButtonsModule } from '@base/shared/components/buttons';
// import { StateToggleModule } from '@base/shared/components/state-toggle';
import { ConfirmationModule } from '@base/shared/confirmation';
import { NamedRoutesModule } from '@libs/named-routes';
// import { AuthorizationModule } from '@tulsa/libs/security';
import { ExtractModelPathModule } from '@libs/utils/extract-model-path';


const COMMON_MODULES = [
  BreadcrumbsModule,
  ConfirmationModule,
  ExtractModelPathModule,
  MatIconModule,
  MatMenuModule,
  MatTooltipModule,
  NamedRoutesModule,
  TranslateModule,
  TswButtonsModule,
  // StateToggleModule,
  // AuthorizationModule,
];

@NgModule({
  imports: COMMON_MODULES,
  exports: COMMON_MODULES,
})
export class CommonsModule {
}
