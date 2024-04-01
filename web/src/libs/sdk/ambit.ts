import {SimpleModel, StatefulAltModel} from "@libs/sdk/common";

export interface Ambit extends SimpleModel,StatefulAltModel{

    name: string;

}

export interface ScopeCampaignForm {
    id: number | null;
    name: string | null;
}