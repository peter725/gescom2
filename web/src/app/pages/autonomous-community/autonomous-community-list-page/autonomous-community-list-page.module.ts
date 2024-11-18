import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { ListPageModule } from '@base/shared/pages/list';
import { NamedRoutesModule } from '@libs/named-routes';
import { AutonomousCommunityListPageRoutingModule } from './autonomous-community-list-page-routing.module';
import { AutonomousCommunityListPageComponent } from './autonomous-community-list-page.component';
import {StateToggleModule} from "@base/shared/components/state-toggle";


@NgModule({
    imports: [
        AutonomousCommunityListPageRoutingModule,
        CommonModule,
        CommonsModule,
        ListPageModule,
        FormsModule,
        MatFormFieldModule,
        MatInputModule,
        NamedRoutesModule,
        StateToggleModule,
    ],
    declarations: [
        AutonomousCommunityListPageComponent
    ],
})
export class AutonomousCommunityListPageModule {
}
