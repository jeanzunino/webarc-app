import { Injectable } from '@angular/core';
import { environment } from '@environment/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private http : HttpClient,
              private storageService: StorageService) { }

  private baseUrl = environment.url;
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.storageService.getUser().token
    })
  }

  public post(url, data): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/${url}`, JSON.stringify(data), this.httpOptions);
  }

  public get(url): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${url}`, this.httpOptions);
  }
}
