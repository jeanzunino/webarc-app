import { HttpClient } from '@angular/common/http';

import { environment } from '@environment/environment';
import { StorageService } from '@service/storage/storage.service';

export class EntityService<T> {

  public baseUrl = environment.url;

  constructor(protected http: HttpClient,
              protected storageService: StorageService,
              protected entityUrl: string) {
  }

  public getAll() {
    return this.http.get<T[]>(`${this.baseUrl}/${this.entityUrl}`);
  }

  public get() {
    return this.http.get<T>(`${this.baseUrl}/${this.entityUrl}`);
  }

  public post(entity: T) {
    return this.http.post<T>(`${this.baseUrl}/${this.entityUrl}`, JSON.stringify(entity));
  }

  public put(entity: T) {
    return this.http.put<T>(`${this.baseUrl}/${this.entityUrl}/entity.id`, JSON.stringify(entity));
  }

  public delete(entity: T) {
    return this.http.delete<T>(`${this.baseUrl}/${this.entityUrl}/entity.id`);
  }
}