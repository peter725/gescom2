import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { CRUD_OPERATIONS, CrudOperationStorage, HttpParamsSrc } from '@libs/crud-api';
import { downloadFile } from '@libs/file/file-downloader';
import { firstValueFrom, Observable } from 'rxjs';
import { DownloadFileConfig } from './export-file.model';


@Injectable()
export class FileDownloaderImplService {
    constructor(
        private http: HttpClient,
        @Inject(CRUD_OPERATIONS) private operations: CrudOperationStorage,
    ) {
    }

    /**
     * Realiza el proceso de descarga
     */
    async downloadFile(config: DownloadFileConfig) {
        const operation = this.getOperation(config.resourceName);
        if (!operation) {
            console.error(`La operación REST "${ config.resourceName }" no existe. Parando la ejecución.`)
            return;
        }

        let request: Observable<HttpResponse<ArrayBuffer>>;

        const columns = (config.columns || []);
        const criteria: Record<string, HttpParamsSrc> = {};
        if (config.queryParams) {
            Object.entries(config.queryParams)
                .filter(([key, value]) => key != null && value != null && value != '')
                .forEach(([key, value]) => criteria[key] = value);
        }

        if (config.method === 'post') {
            const body = {
                columns,
                criteria,
            };
            request = this.http.post(operation.path, body, {
                responseType: 'arraybuffer',
                observe: 'response',
            });
        } else {
            const paramSrc: Record<string, HttpParamsSrc> = {
                ...criteria
            };
            if (config.columns) {
                paramSrc['columns'] = columns.map(v => v.name);
            }

            request = this.http.get(operation.path, {
                responseType: 'arraybuffer',
                observe: 'response',
                params: new HttpParams({ fromObject: paramSrc }),
            });
        }

        const response = await firstValueFrom(request);
        downloadFile(response, config.options);
    }

    private getOperation(resourceName: string) {
        try {
            const def = this.operations.get(resourceName);
            return def.base();
        } catch (e) {
            return null;
        }
    }

}
