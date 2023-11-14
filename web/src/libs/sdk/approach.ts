import {SimpleModel, StatefulModel, TranslatedModel} from "@libs/sdk/common";

export interface Approach extends SimpleModel, StatefulModel, TranslatedModel {
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