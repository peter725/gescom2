import { SimpleModel, TranslatedModel, StatefulModel } from './common';


export interface Origin extends SimpleModel, TranslatedModel, StatefulModel {
  name: string;
}

export interface OriginForm {
  name: string | null;
}
