import { SimpleModel, StatefulModel } from './common';


export interface Language extends SimpleModel, StatefulModel {
  isoCode: string;
  name: string;
}

export const NO_LANGUAGE = -1;
