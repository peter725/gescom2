import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ListPageModule } from '@base/shared/pages/list';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { ApproachListPageRoutingModule } from './approach-list-page-routing.module';
import { ApproachListPageComponent } from './approach-list-page.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {ApproachListPageFilterComponent} from "@base/pages/approach/approach-list-page/components";


@NgModule({
    imports: [
        ApproachListPageRoutingModule,
        CommonModule,
        ListPageModule,
        CommonsModule,
        FormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatNativeDateModule
    ],
    declarations: [
        ApproachListPageComponent,
        ApproachListPageFilterComponent
    ],
})
export class ApproachListPageModule {
}
