import { SimpleModel, StatefulAltModel } from '@libs/sdk/common';
import { AppQuerySource } from '@libs/commons';
import { TypeCampaign } from '@libs/sdk/typeCampaign';
import { Ambit } from '@libs/sdk/ambit';
import { AutonomousCommunity } from '@libs/sdk/autonomousCommunity';
import { Proponent } from '@libs/sdk/proponent';
import { Specialist } from '@libs/sdk/specialist';
import { AbstractControl } from '@angular/forms';
import { IprDTO, ResultsResponseDTO } from './ipr';

export interface Protocol extends SimpleModel, StatefulAltModel {
  id: number;
  code: string;
  name: string;
  campaignId: number;
  createdAt: string;
  Questions: Question[];
  nameCampaign: string;
  question: Question[];
  iprDTOS: IprDTO[];
  resultsResponseDTO: ResultsResponseDTO;

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
  questionsDTO: AbstractControl;
  campaignId: number;
  nameCampaign: string;

}

export interface Infriengement extends SimpleModel, StatefulAltModel {
  code: string;
  infringement: string;
}

export interface Question {
  id: number | null;
  orderQuestion: number | null;
  codeQuestion: number | null;
  question: string | null;
  codeInfringement: string | null;
  bkTrinti: string | null;
  response: any | null;
  bkTrrees: string | null;
  numResponseSi: any;
  numResponseNo: any;
  numResponseNoProcede: any;
}

export interface QuestionIpr {
  id: number | null;
  orderQuestion: number | null;
  formula: number | null;
  question: string | null;
  percentageRespectTo: string | null;

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

export interface QuestionDetailForm {
  question: string;
  infringement: string;
}

export interface ProtocolDetailForm {
  campaignName: string;
  year: string;
  typeCampaign: string;
  ambit: string;
  responsible: string;
  participants: string;
  codeCPA: string;
  protocolName: string;
  questions: QuestionDetailForm[];
}

