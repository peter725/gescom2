import {SimpleModel, StatefulModel} from "@libs/sdk/common";

/**
 * Default email structure.
 */
export interface Email extends SimpleModel, StatefulModel {

    name: string;
}

export interface EmailForm {
    name: string | null;
}