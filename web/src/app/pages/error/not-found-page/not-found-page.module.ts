import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { NamedRoutesModule } from '@tulsa/libs/named-routes';
import { NotFoundPageRoutingModule } from './not-found-page-routing.module';
import { NotFoundPageComponent } from './not-found-page.component';


@NgModule({
  imports: [
    CommonModule,
    NotFoundPageRoutingModule,
    MatButtonModule,
    MatIconModule,
    NamedRoutesModule,
  ],
  declarations: [
    NotFoundPageComponent
  ]
})
export class NotFoundPageModule {
}
