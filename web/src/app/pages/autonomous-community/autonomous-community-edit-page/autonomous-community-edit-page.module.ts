import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonsModule } from '@base/shared/pages/commons.module';
import { EditPageModule } from '@base/shared/pages/edit-page.module';
import { TswSelectModule } from '@base/shared/select';
import { NamedRoutesModule } from '@libs/named-routes';
import { AutonomousCommunityEditPageRoutingModule } from './autonomous-community-edit-page-routing.module';
import { AutonomousCommunityEditPageComponent } from './autonomous-community-edit-page.component';

@NgModule({
    imports: [
        AutonomousCommunityEditPageRoutingModule,
        CommonModule,
        CommonsModule,
        EditPageModule,
        FormsModule,
        MatFormFieldModule,
        MatInputModule,
        NamedRoutesModule,
        TswSelectModule,
    ],
    declarations: [
        AutonomousCommunityEditPageComponent
    ],
})
export class AutonomousCommunityEditPageModule {
}
