import {SimpleModel, StatefulAltModel} from "@libs/sdk/common";

export interface AutonomousCommunity extends SimpleModel,StatefulAltModel{

    name: string;

}

export interface AutonomousCommunityForm {
    id: number | null;
    name: string | null;
}