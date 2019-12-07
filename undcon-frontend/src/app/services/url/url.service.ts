import { Injectable } from '@angular/core';
import { environment } from '@environment/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDetail } from '@models/user/user-detail';

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

  public post(url, data): Observable<UserDetail> {
    console.log(`${this.baseUrl}/${url}`)
    return this.http.post<UserDetail>(`${this.baseUrl}/${url}`, JSON.stringify(data), this.httpOptions);
  }
}
