export interface IprDTO {
  id: number;
  code: string;
  name: string;
  campaignId: number;
  protocolCode: string;
  protocolId: number;
  nameCampaign: string;
  iprQuestionDTOList: IprQuestionDTO[];
}

export interface IprQuestionDTO {
  id: number;
  code: string;
  iprCode: string;
  orderQuestion: number;
  formula: string;
  percentageRespectTo: number;
  question: string;
  iprId: number;
}


