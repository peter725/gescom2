import { SimpleModel, StatefulAltModel, StatefulModel } from './common';
import {TypeCampaign} from "@libs/sdk/typeCampaign";
import { ScopeCampaign } from './scopeCampaign';
import { ResponsableEntityCampaign } from './responsableEntityCampaign';

/**
 * Default user structure.
 */
export interface Campaign extends SimpleModel, StatefulModel {

  year: number;
  code_cpa: number;
  campaign: string;
  type: TypeCampaign;
  scope: ScopeCampaign;
  responsable_entity: string;

}


/**
 * Create a new user request structure.
 */
export interface CreateCampaign {
  id: number | null;

  year: number | null;
  code_cpa: number | null;
  campaign: string | null;
  type: TypeCampaign | null;
  scope: ScopeCampaign | null;
  responsable_entity: ResponsableEntityCampaign | null;

}

