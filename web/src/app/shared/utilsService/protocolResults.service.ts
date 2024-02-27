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
    
    saveResults(producto: ProtocolResults):any{
        const def = this.operations.get(this.resourceUrl);
        const operation = def.findAll();
        return this.http
                        .post<ProtocolResults>(operation.path + '/saveProtocolResults', producto, { observe: 'response' });

    }

}
