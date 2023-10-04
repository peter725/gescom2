import { Observable } from 'rxjs';
import { ChangeStateReq, RequestConfig } from './models';
import { Page } from './page';


export interface CrudService<T, ID> {
  findAny<Result = T>(config: RequestConfig): Observable<Result>;

  findAll<Result = T>(config: RequestConfig): Observable<Page<Result>>;

  findById<Result = T>(id: ID, config: RequestConfig): Observable<Result>;

  create<Result = T>(payload: T, config: RequestConfig): Observable<Result>;

  update<Result = T>(id: ID, payload: T, config: RequestConfig): Observable<Result>;

  changeState<Result = T>(id: ID, payload: ChangeStateReq, config: RequestConfig): Observable<any>;

  delete(id: ID, config: RequestConfig): Observable<void>;
}
