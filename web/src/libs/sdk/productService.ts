import { SimpleModel, StatefulAltModel } from '@libs/sdk/common';

export interface CampaignProductService extends SimpleModel, StatefulAltModel {
  id: number;
  name: string;
}

export interface ProductService {
  id: number | undefined;
  code: string | undefined;
  name: string | undefined;
  bkNoco: string | undefined;
  bkPsdescri: string | undefined;
  bkPsinsel1: string | undefined;
  bkPscon0: string | undefined;
  bkPscoicp: string | undefined;
  state: number | undefined;

}

export interface CampaignProductServiceDTO {
  id: number | undefined;
  productName: string | undefined;
  campaignId: number | undefined;
  codeProductService: string | undefined;
  productServiceId: number | undefined;
}