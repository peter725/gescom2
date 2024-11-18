import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { B64FileAccessorDirective, FileAccessorDirective } from './directives';


const EXPORTED_DECLARATIONS = [
    B64FileAccessorDirective,
    FileAccessorDirective,
];

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: EXPORTED_DECLARATIONS,
    exports: EXPORTED_DECLARATIONS,
})
export class FileUtilsModule {
}