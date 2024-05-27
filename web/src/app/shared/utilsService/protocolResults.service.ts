import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { CRUD_OPERATIONS, CrudOperationStorage, HttpParamsSrc, RequestConfig } from '@libs/crud-api';
import { Protocol } from '@libs/sdk/protocol';
import { CampaignProductServiceDTO, ProductService } from '@libs/sdk/productService';
import { Observable } from 'rxjs';
import { ProtocolResults } from '@libs/sdk/protocolResults';

@Injectable()
export class ProtocolResultsService {
    private resourceUrl = 'protocol_results';

    constructor(private http: HttpClient,
        @Inject(CRUD_OPERATIONS) private operations: CrudOperationStorage,) { }

    // Método para guardar resultados por primera vez
    saveResults(producto: ProtocolResults):any{
        const def = this.operations.get(this.resourceUrl);
        const operation = def.findAll();
        return this.http
                        .post<ProtocolResults>(operation.path + '/saveprotocolresults', producto, { observe: 'response' });

    }

    // Método para actualizar resultados existentes
    updateResults(producto: {
        code: any;
        autonomousCommunityCountryCode: any;
        productServiceId: any;
        campaignId: any;
        autonomousCommunityCountryDTO: any;
        protocolDTO: any;
        autonomousCommunityCountryId: any;
        protocolId: any;
        protocolCode: any;
        protocolName: any;
        productServiceCode: any;
        name: any;
        id: any;
        productServiceDTO: any;
        totalProtocolResultsDTOS: {
            ccaaRep: any;
            code: any;
            ccaaRen: any;
            protocolResultsId: any;
            codeQuestion: any;
            id: any;
            ccaaRes: any;
            protocolResultsCode: any
        }[]
    }): Observable<any> {
        console.log('updateResults',producto.id)
        const def = this.operations.get(this.resourceUrl);
        const operation = def.findAll();
        const url = `${operation.path}/update/${producto.id}`;
        return this.http.post<ProtocolResults>(url, producto, { observe: 'response' });
    }

}
