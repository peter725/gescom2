import {SimpleModel, StatefulAltModel} from "@libs/sdk/common";

export interface TypeCampaign extends SimpleModel,StatefulAltModel{

    name: string;

}

export interface TypeCampaignForm {
    id: number | null;
    name: string | null;
}