// eliminar en cuanto el backend esté disponible

import { Sample } from '@libs/sdk/sample';
import { SimpleUidGenerator } from '@libs/utils/uid';


export const getSample = (opts: {
  sampleId?: number;
  templateId?: number;
  moduleId: number;
  scopeCode: string;
  seasonId: number;
}): Promise<Sample> => {
  const sampleId = opts.sampleId || 1;
  const datasetId = Math.floor(Math.random() * 100) + 1;
  const datasetPubCode = SimpleUidGenerator.createUid();

  const data: Partial<Sample> = {
    sampleId,
    pubCode: datasetPubCode,
    moduleId: opts.moduleId || 1,
    seasonId: opts.seasonId || 1,

    common: {
      datasetId,
      sampId: datasetPubCode,
      sampArea: 'ES30',
      catAlim: 'IA090',
      sampD: '15',
      sampM: '5',
      sampY: '2022',
      progId: 'ES_PROGR_2022',
      progType: 'K009A',
      progLegalRef: 'N027A%N028A%N317A',
      sampMethod: 'N009A',
    },

    results: [
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
        paramCode: 'RF-0020-003-PPP',
        anMethType: 'AT06A',
      },
      /*{
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },
      {
        datasetId,
        resultId: Math.floor(Math.random() * 1000) + 1,
        paramType: 'P002A',
      },*/
    ]
  };

  return new Promise(resolve => setTimeout(() => resolve(data as Sample), 1500));
};
