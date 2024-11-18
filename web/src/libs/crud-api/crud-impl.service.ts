import { HttpClient, HttpParams } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { BaseModel } from '@libs/sdk/common';
import { map, Observable } from 'rxjs';
import { CrudService } from './crud.service';
import { ChangeStateReq, HttpParamsSrc, RequestConfig } from './models';
import { CrudOperationStorage } from './operations';
import { Page, PageResponse } from './page';
import { CRUD_OPERATIONS } from './tokens';


@Injectable({ providedIn: 'root' })
export class CrudImplService<T = BaseModel, ID = number> implements CrudService<T, ID> {

  constructor(
    private http: HttpClient,
    @Inject(CRUD_OPERATIONS) private operations: CrudOperationStorage,
  ) {
  }

  findAny<Result = T>(config: RequestConfig): Observable<Result> {
    const def = this.operations.get(config.resourceName);
    const operation = def.findAll(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.get<Result>(operation.path, { params });
  }

  findAll<Result = T>(config: RequestConfig): Observable<Page<Result>> {
    const def = this.operations.get(config.resourceName);
    const operation = def.findAll(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.get<Result[] | PageResponse<Result>>(operation.path, { params }).pipe(
      map(result => this.pageResponseToPage(result, config.pageReq?.sort)),
    );
  }

  findAllFilter<Result = T>(id: ID, config: RequestConfig): Observable<Page<Result>> {
    const def = this.operations.get(config.resourceName);
    const operation = def.findAllFilter(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.get<Result[] | PageResponse<Result>>(operation.path, { params }).pipe(
      map(result => this.pageResponseToPage(result, config.pageReq?.sort)),
    );
  }
  
  
  findById<Result = T>(id: ID, config: RequestConfig): Observable<Result> {
    const def = this.operations.get(config.resourceName);
    const operation = def.findById(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.get<Result>(operation.path, { params });
  }

  create<Result = T>(payload: T, config: RequestConfig): Observable<Result> {
    const def = this.operations.get(config.resourceName);
    const operation = def.create(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.post<Result>(operation.path, payload, { params });
  }

  update<Result = T>(id: ID, payload: T, config: RequestConfig): Observable<Result> {
    const def = this.operations.get(config.resourceName);
    const operation = def.update({ ...id, ...config.pathParams });
    const params = this.buildHttpParams(config);
    return this.http.post<Result>(operation.path, payload, { params });
  }

  changeState<Result = T>(id: ID, payload: ChangeStateReq, config: RequestConfig): Observable<any> {
    const def = this.operations.get(config.resourceName);
    const operation = def.changeState({ ...id, ...config.pathParams });
    const params = this.buildHttpParams(config);
    return this.http.post<Result>(operation.path, payload, { params });
  }

  delete(id: ID, config: RequestConfig): Observable<void> {
    const def = this.operations.get(config.resourceName);
    const operation = def.delete(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.post<void>(operation.path, {}, { params });
  }

  deleteId(id: ID, config: RequestConfig): Observable<void> {
    const def = this.operations.get(config.resourceName);
    const operation = def.delete(config.pathParams); // Quitamos el spread operator aquí
    const params = this.buildHttpParams(config);
    const url = `${operation.path}/${id}`; // Agregamos el ID del documento a la URL
    return this.http.post<void>(url, { params }); // Usamos el método HTTP DELETE en lugar de POST
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

  private pageResponseToPage<Result = T>(
    response: Result[] | PageResponse<Result>,
    sort?: string[] | undefined,
  ): Page<Result> {
    if (Array.isArray(response)) {
      return Page.unpaged(response, sort);
    }
    return Page.ofPageResponse(response, sort);
  }

}
