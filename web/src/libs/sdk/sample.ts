import { AppQuerySource } from '@libs/commons';
import { AuditedModel, SimpleModel, StatefulModel } from '@libs/sdk/common';
import { SampleDataset, SampleDatasetSave } from './sample-dataset';
import { SampleStateCode } from './sample-state';


export interface Sample extends SampleDataset, StatefulModel {
  sampleId: number;
  sampleStateId: number;
  sampleStateCode: SampleStateCode;
  sampleStateName?: string;

  scopeCode: string;
  pubCode: string;

  templateSrcId: number | null;
  programSrcId: number | null;
}

export interface VSample extends SimpleModel, StatefulModel, AuditedModel {
  scopeCode: string;
  pubCode: string;
  sampleDatasetId: number;
  templateSrcId: number;
  programSrcId: number;
  stateId: number;
  moduleId: number;
  moduleCode: string;
  moduleName: string;
  seasonId: number;
  seasonName: string;
  sampleStateId: number;
  sampleStateCode: string;
  sampleStateName: string;
}

export interface SampleSave extends SimpleModel<number | null>, SampleDatasetSave {
  sampleId: number | null;

  // Si la muestra ha sido creada utilizando una plantilla
  templateSrcId?: number;
  // Si la muestra ha sido creada utilizando la programación
  programSrcId?: number;
}

export interface SampleFilter extends AppQuerySource {
  pubCode?: string;
  sampleStateId?: number;
  seasonName?: string;
  createdAtGTE?: string;
  createdAtLTE?: string;
  updatedAtGTE?: string;
  updatedAtLTE?: string;
  state?: number[];
}
