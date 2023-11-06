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
    console.log("entro aqui findAny");
    const def = this.operations.get(config.resourceName);
    const operation = def.findAll(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.get<Result>(operation.path, { params });
  }

  findAll<Result = T>(config: RequestConfig): Observable<Page<Result>> {
    console.log("entro aqui findAll");
    const def = this.operations.get(config.resourceName);
    const operation = def.findAll(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.get<Result[] | PageResponse<Result>>(operation.path, { params }).pipe(
      map(result => this.pageResponseToPage(result, config.pageReq?.sort)),
    );
  }

  findById<Result = T>(id: ID, config: RequestConfig): Observable<Result> {
    console.log("entro aqui findById");
    const def = this.operations.get(config.resourceName);
    const operation = def.findById(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.get<Result>(operation.path, { params });
  }

  create<Result = T>(payload: T, config: RequestConfig): Observable<Result> {
    console.log("entro aqui create");
    const def = this.operations.get(config.resourceName);
    const operation = def.create(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.post<Result>(operation.path, payload, { params });
  }

  update<Result = T>(id: ID, payload: T, config: RequestConfig): Observable<Result> {
    console.log("entro aqui update");
    const def = this.operations.get(config.resourceName);
    const operation = def.update({ ...id, ...config.pathParams });
    const params = this.buildHttpParams(config);
    return this.http.post<Result>(operation.path, payload, { params });
  }

  changeState<Result = T>(id: ID, payload: ChangeStateReq, config: RequestConfig): Observable<any> {
    console.log("entro aqui changeState");
    const def = this.operations.get(config.resourceName);
    const operation = def.changeState({ ...id, ...config.pathParams });
    const params = this.buildHttpParams(config);
    return this.http.post<Result>(operation.path, payload, { params });
  }

  delete(id: ID, config: RequestConfig): Observable<void> {
    console.log("entro aqui delete");
    const def = this.operations.get(config.resourceName);
    const operation = def.delete(config.pathParams);
    const params = this.buildHttpParams(config);
    return this.http.post<void>(operation.path, {}, { params });
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
