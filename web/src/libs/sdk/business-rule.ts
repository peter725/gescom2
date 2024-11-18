import { AppQuerySource } from '../commons';
import { SimpleModel, StatefulModel, TranslatedModel } from './common';
import { BusinessRuleInfoType } from './info-type';
import { Origin } from './origin';

export interface BusinessRule extends SimpleModel, TranslatedModel, StatefulModel  {
  code: string;
  lastUpdate: Date;
  ignoreNull: string;
  infoType: BusinessRuleInfoType;
  origin: Origin;
  infoMessage: string;
  description: string;
}

export interface VBusinessRule extends SimpleModel {
  id: number;
  code: string;
  description: string;
  languageId: number;
  languageCode: number;
  typeId: number;
  typeCode: string;
  infoTypeId: number;
  infoTypeCode: string;
  infoTypeName: string;
  infoMessage: string;
  ignoreNull: boolean;
  lastUpdate: Date;
  originId: number;
  originName: string;
  }


export interface BusinessRuleForm {
  id: number | null;
  languageId: number | string | null;
  code: string | null;
  ignoreNull: boolean | null;
  infoType: BusinessRuleInfoType | null;
  infoMessage: string | null;
  description: string | null;
}

export interface BusinessRuleFilterForm extends AppQuerySource {
  code?: string;
  lastUpdateGTE?: string;
  lastUpdateLTE?: string;
  state?: number[];
}
