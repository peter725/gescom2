import { SimpleModel, TranslatedModel } from '@libs/sdk/common';

export interface SampleState extends SimpleModel, TranslatedModel {
  code: string;
  name: string;
}

export enum SampleStateCode {
  E01 = 'E01', // Borrador
  // etc
}
