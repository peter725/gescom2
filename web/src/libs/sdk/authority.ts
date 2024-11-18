import {SimpleModel, StatefulModel} from "@libs/sdk/common";

export interface Authority extends SimpleModel, StatefulModel {

    name: string;
    autonomousCommunity: string;
    directionName: string;
    concierge: string;
    address: string;
}

export interface AuthorityForm {
    id: number | null;
    name: string | null;
    autonomousCommunity: string | null;
    directionName: string | null;
    concierge: string | null;
    address: string | null;

}

export interface CreateAuthority {
    id: number | null;
    name: string | null;
    autonomousCommunity: string | null;
    directionName: string | null;
    concierge: string | null;
    address: string | null;
}