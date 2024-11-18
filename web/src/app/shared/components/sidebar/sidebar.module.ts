import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { NamedRoutesModule } from '@libs/named-routes';
import { SidebarToggleDirective } from './sidebar-toggle.directive';
import { SidebarComponent } from './sidebar.component';


const exported = [
  SidebarComponent,
  SidebarToggleDirective,
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
export class SidebarModule {
}
