import { SimpleModel, StatefulAltModel } from '@libs/sdk/common';
import { AppQuerySource } from '@libs/commons';

export interface Protocol extends SimpleModel, StatefulAltModel {
  order: number;
  codeQuestion: number;
  question: string;
  infringement: Infriengement;
  response: boolean;

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

