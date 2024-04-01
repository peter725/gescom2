import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ListPageModule } from '@base/shared/pages/list';
import { CommonsModule } from '@base/shared/pages/commons.module';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {StateToggleModule} from "@base/shared/components/state-toggle";
import { ProtocolListPageRoutingModule } from './protocol-list-page-routing.module';
import { ProtocolListPageComponent } from './protocol-list-page.component';
import { ProtocolListPageFilterComponent } from './components/protocol-list-page-filter/protocol-list-page-filter.component';

@NgModule({
    imports: [
        ProtocolListPageRoutingModule,
        CommonModule,
        ListPageModule,
        CommonsModule,
        FormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatNativeDateModule,
        StateToggleModule
    ],
    declarations: [
        ProtocolListPageComponent,
        ProtocolListPageFilterComponent
    ],
})
export class ProtocolListPageModule {
}
