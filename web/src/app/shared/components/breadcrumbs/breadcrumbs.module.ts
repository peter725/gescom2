import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BreadcrumbsComponent } from './breadcrumbs.component';
import { RouterModule } from '@angular/router';

const declarations = [BreadcrumbsComponent];

@NgModule({
  imports: [CommonModule, RouterModule],
  declarations,
  exports: declarations,
})
export class BreadcrumbsModule {
}
