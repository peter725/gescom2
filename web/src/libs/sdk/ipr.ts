export interface IprDTO {
  id: number;
  code: string;
  name: string;
  campaignId: number;
  protocolCode: string;
  protocolId: number;
  nameCampaign: string;
  product: string;
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

export interface ResultsResponseDTO {
  id: number;
  campaignName: string;
  protocolName: string;
  productName: string;

  questionsResponseDTOS: QuestionsResponseDTO[];

}

export interface QuestionsResponseDTO {
  id: number;
  codeQuestion: string;
  orderQuestion: number;
  question: string;
  total: number;
  percentage: number;
  percentageRespectTo: number;
  numResponseSi: number;
  numResponseNo: number;
  numResponseNoProcede: number;
}




