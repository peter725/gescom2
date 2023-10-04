import { NgModule } from '@angular/core';
import { TitleStrategy } from '@angular/router';
import { CustomTitleStrategyService } from '@base/shared/title';

@NgModule({
  providers: [{
    provide: TitleStrategy,
    useClass: CustomTitleStrategyService,
  }]
})
export class TitleConfig {}
