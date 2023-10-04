import { SimpleModel, TranslatedModel } from '@libs/sdk/common';
import { AppQuerySource } from '../commons';

export interface SampleTemplate extends SimpleModel, TranslatedModel {
  name: string,
  sharedAccess: boolean,
  scopeCode: string,
  moduleId: number,
  seasonId: number,
  seasonName: string,
  providerEntity: string,
  useRangeFrom: string,
  useRangeUntil: string,
  sampleId: number,
  ownerId: number,
}

export interface SampleTemplateForm {
  id: number | null;
  name: string| null;
  sharedAccess: boolean| null;
  scopeCode: string| null;
  moduleId: number| null;
  seasonId: number| null;
  seasonName: string| null;
  providerEntity: string| null;
  useRangeFrom: Date| null;
  useRangeUntil: Date| null;
  sampleId: number| null;
  ownerId: number| null;
}

export interface TulsaSampleTemplateFilterForm extends AppQuerySource {
  name?: string,
  sharedAccess?: boolean,
  seasonName?: string,
  useRangeFrom?: string,
  useRangeUntil?: string,
}
