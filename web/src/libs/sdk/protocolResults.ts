import { AutonomousCommunity } from "./autonomousCommunity";
import { ProductService } from "./productService";
import { Protocol } from "./protocol";

export interface ProtocolResults {
  id: number | undefined;
  autonomousCommunityCountryCode: string | undefined;
  name: string | undefined;
  productServiceCode: string | undefined;
  protocolCode: string | undefined;
  campaignId: number | undefined;
  productServiceId: number | undefined;
  protocolId: number | undefined;
  totalProtocolResultsDTOS: TotalProtocolResults[] | undefined;
  protocolDTO: Protocol | undefined;
  productServiceDTO: ProductService | undefined;
  autonomousCommunityCountryDTO: AutonomousCommunity | undefined;

}

export interface TotalProtocolResults {
  id: number | undefined;
  ccaaRen: number | null;
  ccaaRep: number | null;
  ccaaRes: any;
  code: string | null;
  protocolResultsCode: string | null;
  codeQuestion: any;
  productServiceId: number | undefined;
}