import { Injectable } from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import {User} from "@libs/sdk/user";
import {catchError, throwError} from "rxjs";
@Injectable({
    providedIn: 'root'
  })
  export class UserService {

    private apiURL = "http://localhost:8888/V1/user";
    constructor( private httpClient: HttpClient) {}

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    }
    getUser(): Observable<any>{
        return this.httpClient.get("http://localhost:8080/getUser");
      }

    add(user : User) : Observable<any> {
        return this.httpClient.post(this.apiURL, JSON.stringify(user),this.httpOptions)
            .pipe(catchError(this.errorHandler))
    }

    errorHandler(error : any) {
        let errorMessage = '';
        if(error.error instanceof ErrorEvent) {
            errorMessage = error.error.message;
        } else {
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
        }
        return throwError(errorMessage);
    }
  }