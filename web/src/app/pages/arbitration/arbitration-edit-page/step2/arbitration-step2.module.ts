import {NgModule} from '@angular/core';
import {ArbitrationStep2Component} from './arbitration-step2.component';
import {BreadcrumbsModule} from "@base/shared/components/breadcrumbs";
import {
    ArbitrationStep2RoutingModule
} from "@base/pages/arbitration/arbitration-edit-page/step2/arbitration-step2-routing.module";
import {FormExtensionModule} from "@base/shared/components/form";
import {SectionModule} from "@base/shared/components/section";
import {TranslateModule} from "@ngx-translate/core";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {ChooseFile} from "@libs/file/directives/ChooseFile";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {TswSelectModule} from "@base/shared/select";
import {MatButtonModule} from "@angular/material/button";


@NgModule({
    imports: [
        BreadcrumbsModule,
        ArbitrationStep2RoutingModule,
        ReactiveFormsModule,
        FormExtensionModule,
        SectionModule,
        TranslateModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        TswSelectModule,
        MatButtonModule
    ],
    declarations: [
        ArbitrationStep2Component,
        ChooseFile
    ],
})
export class ArbitrationStep2Module {
}
