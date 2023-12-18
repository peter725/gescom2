import {SimpleModel, StatefulAltModel} from "@libs/sdk/common";

export interface ResponsableEntityCampaign extends SimpleModel,StatefulAltModel{

    name: string;

}

export interface ResponsableEntityCampaignForm {
    id: number | null;
    name: string | null;
}