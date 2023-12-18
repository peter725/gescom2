import {SimpleModel, StatefulModel} from "@libs/sdk/common";

//create a new nationalAutority interface with name, minsty, ministryAcronym, generalDirection, deputyDirectorate, legislation, areaResponsability, commentsAreaResponsability, committee, postalAddress, web, legalReferFunctAuthority, icsms, typeAuthority, publicLaboratories, generalComments
export interface AuthorityOEU extends SimpleModel, StatefulModel {
    name: string;
    minsty: string;
    ministryAcronym: string;
    generalDirection: string;
    deputyDirectorate: string;
    legislation: string;
    areaResponsability: string;
    commentsAreaResponsability: string;
    committee: string;
    postalAddress: string;
    web: string;
    legalReferFunctAuthority: string;
    icsms: string;
    typeAuthority: string;
    publicLaboratories: string;
    generalComments: string;
}

export interface AuthorityOEUView extends SimpleModel, StatefulModel {
    name: string;
    minsty: string;
    ministryAcronym: string;
    generalDirection: string;
    deputyDirectorate: string;
    legislation: string;
    areaResponsability: string;
    commentsAreaResponsability: string;
    committee: string;
    postalAddress: string;
    web: string;
    legalReferFunctAuthority: string;
    icsms: string;
    typeAuthority: string;
    publicLaboratories: string;
    generalComments: string;
}

//create a new nationalAutority request structure
export interface CreateAuthorityOEU {
    id: number | null;
    name: string | null;
    minsty: string | null;
    ministryAcronym: string | null;
    generalDirection: string | null;
    deputyDirectorate: string | null;
    legislation: string | null;
    areaResponsability: string | null;
    commentsAreaResponsability: string | null;
    committee: string | null;
    postalAddress: string | null;
    web: string | null;
    legalReferFunctAuthority: string | null;
    icsms: string | null;
    typeAuthority: string | null;
    publicLaboratories: string | null;
    generalComments: string | null;
}



