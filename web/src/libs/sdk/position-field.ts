import { SimpleModel, TranslatedModel, StatefulModel } from './common';


export interface PositionField extends SimpleModel, TranslatedModel, StatefulModel {
  name: string;
  type: string;

  positionIsRepeatable?: boolean;
}

export interface PositionFieldForm {
  name: string | null;
}
