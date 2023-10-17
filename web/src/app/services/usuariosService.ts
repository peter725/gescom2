import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
@Injectable({
    providedIn: 'root'
  })
  export class UsuariosService {
    constructor( private httpClient: HttpClient) {}
    getUser(): Observable<any>{
        return this.httpClient.get("http://localhost:8080/getUser");
      }
  }