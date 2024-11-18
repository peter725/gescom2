import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { CRUD_OPERATIONS, CrudOperationStorage, HttpParamsSrc, RequestConfig } from '@libs/crud-api';
import { Protocol } from '@libs/sdk/protocol';
import { CampaignProductServiceDTO, ProductService } from '@libs/sdk/productService';
import { Observable } from 'rxjs';
import { PhaseCampaign } from '@libs/sdk/phaseCampaign';

@Injectable()
export class CampaignService {
    private resourceUrlProduct = 'campaignProductService';
    private resourceUrlCampaign = 'campaign';

    constructor(private http: HttpClient,
        @Inject(CRUD_OPERATIONS) private operations: CrudOperationStorage,) { }
    
    saveCampaignProduct(producto: CampaignProductServiceDTO[]):any{
        const def = this.operations.get(this.resourceUrlProduct);
        const operation = def.findAll();
        return this.http
                        .post<CampaignProductServiceDTO[]>(operation.path + '/saveRelation', producto, { observe: 'response' });

    }

    deleteProduct(id: number): Observable<any> {
        const config: RequestConfig = {
            resourceName: this.resourceUrlProduct,
        };
        config.pathParams = {id};
        const def = this.operations.get(this.resourceUrlProduct);
        const operation = def.delete(config.pathParams);
        const params = this.buildHttpParams(config);
        return this.http.delete<void>(`${operation.path}/deleteRelation/${id}`, { params });
        // return this.http.delete(`${operation.path}/${id}`, { observe: 'response' });
    }

    saveChangePhase(campaignId: number, phase: PhaseCampaign):any{
      const def = this.operations.get(this.resourceUrlCampaign);
      const operation = def.findAll();
      return this.http
                      .post<any>(`${operation.path}/${campaignId}/phase`, phase, { observe: 'response' });

  }

    private buildHttpParams(config: RequestConfig) {
        const { queryParams, pageReq } = config;
        const fromObject: Record<string, HttpParamsSrc> = {};
    
        // Maybe implement some dynamic value replacement, but IDK
        // this can also be implemented as an interceptor
        Object.entries({ ...queryParams, ...pageReq })
          .filter(([key, value]) => key != null && value != null && value != '')
          .forEach(([key, value]) => fromObject[key] = value);
    
        return new HttpParams({ fromObject });
    }

}
