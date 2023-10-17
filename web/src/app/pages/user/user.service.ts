import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "@base/pages/user/user";
import {catchError, Observable, throwError} from "rxjs";
import {UserCriterial} from "@base/pages/user/userCriterial";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private apiURL = "http://localhost:8090/rest/api/users";

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
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

    constructor(private httpClient : HttpClient) { }

    add(user : User) : Observable<any> {
        return this.httpClient.post(this.apiURL, JSON.stringify(user),this.httpOptions)
            .pipe(catchError(this.errorHandler))
    }

    all(userCriterial : UserCriterial) : Observable<any> {
        let httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json'}),

        }
        return this.httpClient.post(this.apiURL + '/page',JSON.stringify(userCriterial),this.httpOptions);
    }
}