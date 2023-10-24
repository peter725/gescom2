export interface SampleDataset {
  moduleId: number;
  moduleName: string;
  moduleCode: string;
  seasonId: number;
  seasonName: string;

  common: SampleDatasetValue;
  results: SampleDatasetResultValue[];
}

export interface SampleDatasetSave {
  moduleId: number;
  seasonId: number;
  scopeCode: string;

  common: SampleDatasetValue;
  results: SampleDatasetResultValue[];
}

export interface SampleDatasetCtx {
  // Los valores dinámicos serán de tipo string. Typescript no
  // permite definirlos como tal por limitaciones del "index signature"
  [key: string]: any;
}

export interface SampleDatasetValue extends SampleDatasetCtx {
  datasetId: number | null;
}

export interface SampleDatasetResultValue extends SampleDatasetValue {
  resultId: number | null;
  // pubCode: string; // código público del resultado
}
