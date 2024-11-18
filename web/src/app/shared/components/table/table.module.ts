import { DragDropModule } from '@angular/cdk/drag-drop';
import { CdkTableModule } from '@angular/cdk/table';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSortModule } from '@angular/material/sort';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TranslateModule } from '@ngx-translate/core';
import {
  TableContainerComponent,
  TableEmptyRowComponent,
  TableSettingsComponent,
  TswTableComponent
} from './components';
import {FullScreenElementModule} from "@base/shared/full-screen-element";
import { FileDownloaderImplService } from '../../export-file/file-downloader-impl.service';
import {MatTableModule} from "@angular/material/table";
import { PAGINATOR_INTL_PROVIDER, PaginatorConfig } from '@base/config/paginator.config';


const EXPORTED_DECLARATIONS = [
  TableContainerComponent,
  TableEmptyRowComponent,
  TableSettingsComponent,
  TswTableComponent,
];

@NgModule({
    imports: [
        CommonModule,
        MatButtonModule,
        MatCardModule,
        MatIconModule,
        MatPaginatorModule,
        CdkTableModule,
        MatTableModule,
        MatTooltipModule,
        MatMenuModule,
        MatProgressSpinnerModule,
        MatProgressBarModule,
        MatSortModule,
        MatDialogModule,
        DragDropModule,
        MatCheckboxModule,
        TranslateModule,
        FullScreenElementModule,
    ],
    declarations: EXPORTED_DECLARATIONS,
    providers: [FileDownloaderImplService, PAGINATOR_INTL_PROVIDER],
    exports: [
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        ...EXPORTED_DECLARATIONS
    ],
})
export class TableModule {
}
