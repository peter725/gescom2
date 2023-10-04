import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExtractModelPathPipe } from './extract-model-path.pipe';


const EXPORTED_DECLARATIONS = [
  ExtractModelPathPipe,
];

@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: EXPORTED_DECLARATIONS,
  exports: EXPORTED_DECLARATIONS,
})
export class ExtractModelPathModule { }
