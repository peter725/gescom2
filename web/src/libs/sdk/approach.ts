import {SimpleModel, StatefulModel, TranslatedModel} from "@libs/sdk/common";
import {AppQuerySource} from "@libs/commons";

export interface Approach extends SimpleModel, StatefulModel, TranslatedModel {
    autonomousCommunity: string;
    approach: string;
    justification: string;
    objetive: string;
    viability: string;
    campaignTypeId: number;
}

export interface CreateApproach {
    id: number | null;
    autonomousCommunity: string | null;
    approach: string | null;
    justification: string | null;
    objective: string | null;
    viability: string | null;
    campaignTypeId: number | null;

}

export interface ApproachForm {
    id: number | null;
    approach: string | null;
    justification: string | null;
    objective: string | null;
    viability: string | null;
    campaignTypeId: number | null;
}

export interface ApproachFilterForm extends AppQuerySource {
    approach?: string;
    autonomousCommunity?: string;
    campaignTypeId?: number;
    createdAtGTE?: string;
    createdAtLTE?: string;
    updatedAtGTE?: string;
    updatedAtLTE?: string;

}