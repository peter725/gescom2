import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CampaignProductService } from './campaignProduct.service';

@NgModule({
    imports: [
    ],
    providers: [
        CampaignProductService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UtilsServiceModule {}
