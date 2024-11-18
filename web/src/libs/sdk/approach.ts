import {SimpleModel, StatefulModel, TranslatedModel} from "@libs/sdk/common";
import {AppQuerySource} from "@libs/commons";

export interface Approach extends SimpleModel, StatefulModel, TranslatedModel {
    autonomousCommunity: string;
    approach: string;
    justification: string;
    objetive: string;
    viability: string;
    campaignTypeId: number;
    campaignTypeName: string;

}


export interface CreateApproach {
    id: number | null;
    autonomousCommunityName: string | null;
    approach: string | null;
    justification: string | null;
    objective: string | null;
    viability: string | null;
    campaignTypeId: number | null;
    campaignTypeName: string | null;
    year: number | null;
    date: [] | null;

}

export interface ApproachForm {
    id: number | null;
    approach: string | null;
    justification: string | null;
    objective: string | null;
    viability: string | null;
    campaignTypeId: number | null;
    campaignTypeName: string | null;
}

export interface ApproachFilterForm extends AppQuerySource {
    year?: number;
    approach?: string;
    autonomousCommunity?: string;
    type?: string;
    state?: number;
    // createdAtGTE?: string;
    // createdAtLTE?: string;
    // updatedAtGTE?: string;
    // updatedAtLTE?: string;

}