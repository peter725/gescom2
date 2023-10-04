import {SimpleModel, StatefulModel, TranslatedModel} from './common';

export interface Arbitration extends SimpleModel, StatefulModel, TranslatedModel {
  section: string;
  sectionCode: string;

  elementCode: string;
  elementName: string;
  elementLabel: string;
  elementNameWeb: string;
  elementMaxLength: string;
  elementTerminology: string | null;
  description: string;
  version: string;

  origin: Record<string, any>;
  elementFormat: Record<string, any>;
  fieldType: Record<string, any>;
}

export interface ArbitrationForm {
  id: number | null;

  arbitrationType: Record<string, any> | null;
  claimantType:number |null;

  name: string | null;
  socialReason: string | null;
  nif: string | null;
  surnames: string | null;
  phone: string | null;
  email: string | null;

  roadType:  Record<string, any> | null;
  address: string | null;
  number: string | null;
  block: string | null;
  portal: string | null;
  ladder: string | null;
  floor: string | null;
  door: string | null;
  town: Record<string, any> | null;
  province: Record<string, any> | null;
  postalCode: string | null;

  notificationType:  Record<string, any> | null;

  claimedName: string | null;
  claimedNif: string | null;
  claimedProvince: Record<string, any> | null;
  claimedPhone: string | null;
  claimedEmail: string | null;

  claimContent: string | null;
  presentation: string | null;
  observation: string | null;
}
