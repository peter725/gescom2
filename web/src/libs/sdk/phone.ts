import {SimpleModel, StatefulModel} from "@libs/sdk/common";

/**
 * Default email structure.
 */
export interface Phone extends SimpleModel, StatefulModel {

    phone: string;
}

export interface PhoneForm {
    phone: string | null;
}