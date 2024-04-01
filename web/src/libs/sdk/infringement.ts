import { SimpleModel, StatefulAltModel, StatefulModel } from '@libs/sdk/common';
import { AppQuerySource } from '@libs/commons';

export interface Infringement extends SimpleModel, StatefulModel {

  infringement: string;
      code: string;
}

export interface InfringementView extends SimpleModel, StatefulAltModel {

  infringement: string;
        code: string;
}

export interface CreateInfringementForm {

    id: number | null;
  infringement: string | null;
    code: string | null;
}

export interface InfringementFilterForm extends AppQuerySource{

  infringement?: string;
    code?: string;
}