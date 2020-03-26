import { HttpClient } from '@angular/common/http';
import { environment } from '@environment/environment';

import { StorageService } from '@service/storage/storage.service';

export class EntityService<T> {

  public baseUrl = environment.url;

  constructor(protected http: HttpClient,
              protected storageService: StorageService,
              protected entityUrl: string) {
  }

  public post(entity: T) {
    return this.http.post<T>(`${this.baseUrl}/${this.entityUrl}`, JSON.stringify(entity));
  }

  public get() {
    return this.http.get<T>(`${this.baseUrl}/${this.entityUrl}`);
  }

  public getAll() {
    return this.http.get<T[]>(`${this.baseUrl}/${this.entityUrl}`);
  }
}
