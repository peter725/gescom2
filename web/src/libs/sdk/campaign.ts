import { SimpleModel, StatefulAltModel, StatefulModel } from './common';
import {TypeCampaign} from "@libs/sdk/typeCampaign";
import { Ambit } from './ambit';
import { AutonomousCommunity } from "@libs/sdk/autonomousCommunity";
import { Proponent } from "@libs/sdk/proponent";
import { Specialist } from "@libs/sdk/specialist";

/**
 * Default Campaign structure.
 */
export interface Campaign extends SimpleModel, StatefulModel {

  year: number;
  codeCpa: string;
  nameCampaign: string;
  campaignType: TypeCampaign;
  ambit: Ambit;
  responsible_entity: string;

}


/**
 * Create a new Campaign request structure.
 */
export interface CreateCampaign {
  id: number | null;

  year: number | null;
  codeCpa: string | null;
  nameCampaign: string | null;
  campaignType: TypeCampaign[] | null;
  ambit: Ambit | null;
  responsibleEntity: AutonomousCommunity | null;
  participants: AutonomousCommunity[] | null;
  proponents: Proponent[] | null;
  specialists: Specialist[] | null;

}

