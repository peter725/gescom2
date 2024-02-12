import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { CRUD_OPERATIONS, CrudOperationStorage } from '@libs/crud-api';
import { Protocol } from '@libs/sdk/protocol';

@Injectable()
export class ExcelService {
    private resourceUrl = 'phaseCampaign';

    constructor(private http: HttpClient,
        @Inject(CRUD_OPERATIONS) private operations: CrudOperationStorage,) { }
    
    exportExcel(datosExport: Protocol):any{
        const def = this.operations.get(this.resourceUrl);
        const operation = def.findAll();
        return this.http.post(operation.path + '/exportExcel', datosExport, { observe: 'response', responseType: 'blob' }).pipe(map(
            (res: any) => (new Blob([res.body], { type: ' application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' }))
        ));
    }
}
