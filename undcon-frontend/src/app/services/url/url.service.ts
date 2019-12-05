import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  private baseUrl = environment.url;
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http : HttpClient) { }

  public post(url, data): Observable<any> {
    console.log(`${this.baseUrl}/${url}`)
    return this.http.post(`${this.baseUrl}/${url}`, JSON.stringify(data), this.httpOptions);
  }
}
