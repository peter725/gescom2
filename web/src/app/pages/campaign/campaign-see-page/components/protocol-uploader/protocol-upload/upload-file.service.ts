import {Injectable} from "@angular/core";
import {CrudImplService} from "@libs/crud-api";
import {DocumentForm} from "@libs/sdk/document";

@Injectable({providedIn: 'root'})
export class UploadFileService {


    constructor(protected crudService: CrudImplService<DocumentForm>) {
    }




}
