import {NgModule} from "@angular/core";import {CommonModule} from "@angular/common";
import {CommonsModule} from "@base/shared/pages/commons.module";
import {EditPageModule} from "@base/shared/pages/edit-page.module";
import {MatRadioModule} from "@angular/material/radio";
import {TabPanelModule} from "@base/shared/components/tabpanel";import { ReactiveFormsModule } from '@angular/forms';
import {
    ProtocolAddPageComponent
} from '@base/pages/campaign/campaign-see-page/components/protocol/protocol-add-page/protocol-add-page.component';
import {
    ProtocolAddPageRountingModule
} from '@base/pages/campaign/campaign-see-page/components/protocol/protocol-add-page/protocol-add-page-routing.module';

@NgModule({
imports: [
    ReactiveFormsModule,
    CommonModule,
    ProtocolAddPageRountingModule,
    CommonsModule,
    EditPageModule,
    MatRadioModule,
    TabPanelModule,
],
    declarations: [
        ProtocolAddPageComponent],
})
export class ProtocolAddPageModule {
}
