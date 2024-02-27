import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CampaignProductService } from './campaignProduct.service';
import { ProtocolResultsService } from './protocolResults.service';

@NgModule({
    imports: [
    ],
    providers: [
        CampaignProductService,
        ProtocolResultsService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UtilsServiceModule {}
