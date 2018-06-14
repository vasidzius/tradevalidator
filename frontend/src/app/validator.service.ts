import { Injectable } from '@angular/core';
import {environment} from "../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs/internal/observable/throwError";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  public validateTrades( trades: string): Observable<string> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };
    return this.httpClient.post<string>(API_URL + '/', trades, httpOptions )
      .pipe(catchError(this.handleError))
  }

  public shutdownServer(): Observable<Object> {
    return this.httpClient.post(API_URL + '/actuator/shutdown', null)
      .pipe(catchError(this.handleError))
  }

  private handleError( error: Response | any ) {
    console.error('ApiService::handleError', error);
    return throwError(error);
  };
}
