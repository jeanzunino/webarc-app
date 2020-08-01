import { HttpClient } from '@angular/common/http';

import { Entity } from '@model/entity';
import { environment } from '@environment/environment';
import { StorageService } from '@service/storage/storage.service';

export class EntityService<T> {
  public baseUrl = environment.url;

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService,
    protected entityUrl: string
  ) {}

  public getAll(filters?: Map<string, string>, pageNumber: number = 0, sizeNumber: number = 10) {
    let filterAsString = '';
    if (filters) {
      filters.forEach((value: string, key: string) => {
        if (value) {
          filterAsString += '&' + key + value;
        }
      });
    }
    let params: {};
    params = {filter: filterAsString, page: pageNumber, size: sizeNumber };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}`, { params });
  }

  public get(id: number) {
    return this.http.get<T>(`${this.baseUrl}/${this.entityUrl}/${id}`);
  }

  public post(entity: T) {
    return this.http.post<T>(`${this.baseUrl}/${this.entityUrl}`, entity);
  }

  public put(entity: Entity) {
    return this.http.put<T>(
      `${this.baseUrl}/${this.entityUrl}/${entity.id}`,
      entity
    );
  }

  public delete(entity: Entity) {
    return this.http.delete<T>(
      `${this.baseUrl}/${this.entityUrl}/${entity.id}`
    );
  }
}
