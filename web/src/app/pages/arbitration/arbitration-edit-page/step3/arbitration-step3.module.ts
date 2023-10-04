import {NgModule} from '@angular/core';
import {ArbitrationStep3Component} from './arbitration-step3.component';
import {
    ArbitrationStep3RoutingModule
} from "@base/pages/arbitration/arbitration-edit-page/step3/arbitration-step3-routing.module";
import {BreadcrumbsModule} from "@base/shared/components/breadcrumbs";
import {FormExtensionModule} from "@base/shared/components/form";
import {TranslateModule} from "@ngx-translate/core";
import {ReactiveFormsModule} from "@angular/forms";
import {SectionModule} from "@base/shared/components/section";
import {TswButtonsModule} from "@base/shared/components/buttons";


@NgModule({
    imports: [
        ArbitrationStep3RoutingModule,
        BreadcrumbsModule,
        FormExtensionModule,
        TranslateModule,
        ReactiveFormsModule,
        SectionModule,
        TswButtonsModule
    ],
    declarations: [
        ArbitrationStep3Component
    ],
})
export class ArbitrationStep3Module {
}
