import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { TranslateModule } from '@ngx-translate/core';
import { ContextChangeLoaderComponent } from './context-change-loader/context-change-loader.component';


const EXPORTED_DECLARATIONS = [
    ContextChangeLoaderComponent,
];

@NgModule({
    imports: [
        CommonModule,
        MatProgressSpinnerModule,
        MatDialogModule,
        TranslateModule,
    ],
    declarations: EXPORTED_DECLARATIONS,
    exports: EXPORTED_DECLARATIONS,
})
export class AppContextModule {
}
