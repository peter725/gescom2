import { RequestParams } from '@libs/crud-api';
import { DownloadOptions } from '@libs/file';


export type DownloadFileColumnDef = {
    name: string,
    label: string
};

// este es un modelo
export type DownloadFileConfig = {
    resourceName: string;
    options: DownloadOptions;
    method?: 'get' | 'post';
    queryParams?: RequestParams;
    columns?: DownloadFileColumnDef[];
};

export enum ExportFileType {
    EXCEL = 'xslx',
    CSV = 'csv',
    PDF = 'pdf',
    XML = 'xml',
    JSON = 'json',
}
