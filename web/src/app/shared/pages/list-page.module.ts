import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { TableModule } from '../components/table';
import { FilterModule } from '../filter';


const COMMON_MODULES = [
  FilterModule,
  MatCheckboxModule,
  TableModule,
  ReactiveFormsModule,
];

@NgModule({
  imports: COMMON_MODULES,
  exports: COMMON_MODULES,
})
export class ListPageModule {
}
