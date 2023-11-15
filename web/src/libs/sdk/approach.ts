import {SimpleModel, StatefulModel, TranslatedModel} from "@libs/sdk/common";
import {AppQuerySource} from "@libs/commons";

export interface Approach extends SimpleModel, StatefulModel, TranslatedModel {
    year: number;
    autonomousCommunity: string;
    approach: string;
    justification: string;
    objetive: string;
    viability: string;
    type: string;
}

export interface ApproachForm {
    id: number | null;
    approach: string | null;
    justification: string | null;
    objective: string | null;
    viability: string | null;
    type: string | null;
}

export interface ApproachFilterForm extends AppQuerySource {
    year?: number;
    approach?: string;
    autonomousCommunity?: string;
    type?: string;
    createdAtGTE?: string;
    createdAtLTE?: string;
    updatedAtGTE?: string;
    updatedAtLTE?: string;

}