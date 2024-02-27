export interface ProtocolResults {
  id: number | undefined;
  autonomousCommunityCountryCode: string | undefined;
  name: string | undefined;
  productServiceCode: string | undefined;
  protocolCode: string | undefined;
  campaignId: number | undefined;
  productServiceId: number | undefined;
  protocolId: number | undefined;
  totalProtocolResults: TotalProtocolResults[] | undefined;

}

export interface TotalProtocolResults {
  id: number | undefined;
  ccaa_ren: number | null;
  ccaa_rep: number | null;
  ccaa_res: number | null;
  code: string | null;
  protocolResultsCode: string | null;
  codeQuestion: string | null;
  productServiceId: number | undefined;
}