import { SimpleModel, TranslatedModel, StatefulModel } from './common';


export interface BusinessRuleInfoType extends SimpleModel, TranslatedModel, StatefulModel {
  code: string | null;
/*   name: string | null; */
}

export interface BusinessRuleInfoTypeForm {
  id: number | null;
  languageId: number | string | null;
  code: string | null;
 /*  name: string | null; */
}
