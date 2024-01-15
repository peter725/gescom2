import { SimpleModel, StatefulAltModel, StatefulModel } from '@libs/sdk/common';
import { AppQuerySource } from '@libs/commons';

export interface Infraction extends SimpleModel, StatefulModel {

      name: string;
      code: string;
}

export interface InfractionView extends SimpleModel, StatefulAltModel {

        name: string;
        code: string;
}

export interface CreateInfractionForm {

    id: number | null;
    name: string | null;
    code: string | null;
}

export interface InfractionFilterForm extends AppQuerySource{

    name?: string;
    code?: string;
}