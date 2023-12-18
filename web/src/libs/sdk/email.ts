import {SimpleModel, StatefulModel} from "@libs/sdk/common";
import {AppQuerySource} from "@libs/commons";

/**
 * Default email structure.
 */
export interface Email extends SimpleModel, StatefulModel {
    name: string;
}

export interface EmailForm extends AppQuerySource{
    name?: string | null;
}