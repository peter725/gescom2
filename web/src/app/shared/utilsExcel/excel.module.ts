import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ExcelService } from './excel.service';

@NgModule({
    imports: [
    ],
    providers: [
        ExcelService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelModule {}
