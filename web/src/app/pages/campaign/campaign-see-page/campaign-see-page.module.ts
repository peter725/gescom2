import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {CommonsModule} from "@base/shared/pages/commons.module";
import {EditPageModule} from "@base/shared/pages/edit-page.module";
import {MatRadioModule} from "@angular/material/radio";
import {TabPanelModule} from "@base/shared/components/tabpanel";
import { CampaignSeePageRoutingModule } from '@base/pages/campaign/campaign-see-page/campaign-see-page-routing.module';
import { CampaignSeePageComponent } from '@base/pages/campaign/campaign-see-page/campaign-see-page.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { StateToggleModule } from '@base/shared/components/state-toggle';
import { MatTableModule } from '@angular/material/table';
import { BreadcrumbsModule } from '@base/shared/components/breadcrumbs';
import { ReactiveFormsModule } from '@angular/forms';
import { FormExtensionModule } from '@base/shared/components/form';
import { SectionModule } from '@base/shared/components/section';
import { TranslateModule } from '@ngx-translate/core';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { TswSelectModule } from '@base/shared/select';
import { MatButtonModule } from '@angular/material/button';
import { MatSortModule } from '@angular/material/sort';
import { TableModule } from '@base/shared/components/table';
import { ProtocolListComponent } from '@base/pages/campaign/campaign-see-page/components';
import { MatGridListModule } from '@angular/material/grid-list';  
import { UploadFileComponent } from '@base/pages/campaign/campaign-see-page/components/protocol-uploader/protocol-upload/upload-file.component';




@NgModule({
  imports: [
    ProtocolListComponent,
    BreadcrumbsModule,
    ReactiveFormsModule,
    FormExtensionModule,
    SectionModule,
    TranslateModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    TswSelectModule,
    MatButtonModule,
    TabPanelModule,
    CommonModule,
    CampaignSeePageRoutingModule,
    CommonsModule,
    EditPageModule,
    MatRadioModule,
    TabPanelModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    StateToggleModule,
    MatTableModule,
    MatSortModule,
    TableModule,
    MatGridListModule,
    UploadFileComponent 
  ],
    declarations: [
        CampaignSeePageComponent,
        ],
    exports: [
        CampaignSeePageComponent,
    ]
})
export class CampaignSeePageModule {
}