import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '@environment/environment';

import { StorageService } from '@service/storage/storage.service';

export class EntityService<T> {

  baseUrl = environment.url;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService,
    protected entityUrl: string
  ) {
  }

  public post(entity: T) {
    if (this.storageService.getUser())
      this.httpOptions.headers.set('Authorization', this.storageService.getUser().token);
    return this.http.post<T>(`${this.baseUrl}/${this.entityUrl}`, JSON.stringify(entity), this.httpOptions);
  }

  public get() {
    if (this.storageService.getUser())
      this.httpOptions.headers.set('Authorization', this.storageService.getUser().token);
    return this.http.get<T>(`${this.baseUrl}/${this.entityUrl}`, this.httpOptions);
  }

  public getAll() {
    if (this.storageService.getUser())
      this.httpOptions.headers.set('Authorization', this.storageService.getUser().token);
    return this.http.get<T[]>(`${this.baseUrl}/${this.entityUrl}`, this.httpOptions);
  }
}
