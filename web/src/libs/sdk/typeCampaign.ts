import { SimpleModel, StatefulModel } from '@libs/sdk/common';

export interface TypeCampaign extends SimpleModel,StatefulModel{

    name: string;

}

export interface TypeCampaignForm {
    id: number | null;
    description: string | null;
}