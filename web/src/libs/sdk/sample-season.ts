import { AppQuerySource } from '../commons';
import { SimpleModel, StatefulModel } from './common';
import { Module } from './module';


export interface SampleSeason extends SimpleModel, StatefulModel {
  name: string;
  module: Module;
  sampleRegisterStart: Date;
  sampleRegisterEnd: Date;
  efsaDeliveryStart: Date;
  efsaDeliveryEnd: Date;
  efsaAmendmentEnd: Date;
}

export interface SampleSeasonForm {
  id: number | null;
  name: string | null;
  module: Module | null;
  sampleRegisterStart: Date | null;
  sampleRegisterEnd: Date | null;
  efsaDeliveryStart: Date | null;
  efsaDeliveryEnd: Date | null;
  efsaAmendmentEnd: Date | null;
}

export interface SampleSeasonFilterForm extends AppQuerySource {
  name?: string;
  sampleRegisterStartGTE?: string;
  sampleRegisterStartLTE?: string;
  sampleRegisterEndGTE?: string;
  sampleRegisterEndLTE?: string;
  efsaDeliveryStartGTE?: string;
  efsaDeliveryStartLTE?: string;
  efsaDeliveryEndGTE?: string;
  efsaDeliveryEndLTE?: string;
  efsaAmendmentEndGTE?: string;
  efsaAmendmentEndLTE?: string;
  state?: number[];
}
