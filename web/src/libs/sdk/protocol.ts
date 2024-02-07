import { SimpleModel, StatefulAltModel } from '@libs/sdk/common';
import { AppQuerySource } from '@libs/commons';
import { TypeCampaign } from '@libs/sdk/typeCampaign';
import { Ambit } from '@libs/sdk/ambit';
import { AutonomousCommunity } from '@libs/sdk/autonomousCommunity';
import { Proponent } from '@libs/sdk/proponent';
import { Specialist } from '@libs/sdk/specialist';
import { AbstractControl } from '@angular/forms';

export interface Protocol extends SimpleModel, StatefulAltModel {
  order: number;
  codeQuestion: number;
  question: string;
  infringement: Infriengement;
  response: boolean;

}

export interface FilaProtocol {
  id: number | null;
  order: number | null;
  codeQuestion: number | null;
  question: string | null;
  infringement: Infriengement | null;
  response: boolean | null;
}

export interface CreateProtocol {
  name: string;
  code: string;
  rows: AbstractControl;
  campaignId: number;

}

export interface Infriengement extends SimpleModel, StatefulAltModel {
  code: string;
  infringement: string;
}

export interface ProtocolForm {
  id: number | null;
  order: number | null;
  codeQuestion: number | null;
  question: string | null;
  infringement: Infriengement | null;
  response: boolean | null;
}

export interface InfriengementForm {
  id: number | null;
  code: string | null;
  infringement: string | null;
}

export interface ProtocolFilterForm extends AppQuerySource {
  createdAtGTE?: string;
  createdAtLTE?: string;
  updatedAtGTE?: string;
  updatedAtLTE?: string;

}

