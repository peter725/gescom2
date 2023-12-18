import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {ApproachSeePageRoutingModule} from "@base/pages/approach/approach-see-page/approach-see-page-routing.module";
import {TableModule} from "@base/shared/components/table";
import { CommonsModule } from '@base/shared/pages/commons.module';
import {MatDialogModule} from "@angular/material/dialog";
import {StateToggleModule} from "@base/shared/components/state-toggle";
import {ApproachSeePageComponent} from "@base/pages/approach/approach-see-page/approach-see-page.component";
import {EditPageModule} from "@base/shared/pages/edit-page.module";
import {TabPanelModule} from "@base/shared/components/tabpanel";


@NgModule({
    imports: [
        CommonModule,
        ApproachSeePageRoutingModule,
        CommonsModule,
        EditPageModule,
        TableModule,
        MatDialogModule,
        StateToggleModule,
        TabPanelModule,
    ],
    declarations: [
        ApproachSeePageComponent,
    ],
})
export class ApproachSeePageModule {
}