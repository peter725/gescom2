import { PageReq } from './page';


export type HttpParamsSrc = string | number | boolean | ReadonlyArray<string | number | boolean>;

export type RequestParams = Record<string, HttpParamsSrc>

export interface ParamRequestConfig {
  queryParams?: RequestParams;
  pathParams?: RequestParams;
}

export interface RequestConfig extends ParamRequestConfig{
  resourceName: string;
  pageReq?: PageReq;
}

export interface ChangeStateReq {
  status: number;
}
