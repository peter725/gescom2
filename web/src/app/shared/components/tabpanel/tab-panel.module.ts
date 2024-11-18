import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {RouterModule} from '@angular/router';
import {TranslateModule} from '@ngx-translate/core';
import {NamedRoutesModule} from '@libs/named-routes';
import {TabPanelComponent} from "@base/shared/components/tabpanel/tab-panel.component";


const exported = [
  TabPanelComponent,
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    NamedRoutesModule,
    TranslateModule,
    MatIconModule,
  ],
  declarations: exported,
  exports: exported,
})
export class TabPanelModule {
}
