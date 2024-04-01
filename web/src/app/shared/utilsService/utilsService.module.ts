import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CampaignService } from './campaign.service';
import { ProtocolResultsService } from './protocolResults.service';

@NgModule({
    imports: [
    ],
    providers: [
        CampaignService,
        ProtocolResultsService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UtilsServiceModule {}
