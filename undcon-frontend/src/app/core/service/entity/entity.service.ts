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
          filterAsString += ',' + key + value;
        }
      });
      //Elimina a primeira vírgula do filtro
      filterAsString = filterAsString.substr(1);
    }
    
    let params: {};
    params = {filter: filterAsString, page: pageNumber, size: sizeNumber };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}`, { params });
  }

  public getAllCustomUrl(url: string, filters?: Map<string, string>, pageNumber: number = 0, sizeNumber: number = 10) {
    let filterAsString = '';
    if (filters) {
      filters.forEach((value: string, key: string) => {
        if (value) {
          filterAsString += ',' + key + value;
        }
      });
      //Elimina a primeira vírgula do filtro
       filterAsString = filterAsString.substr(1);
    }
    
    let params: {};
    params = {filter: filterAsString, page: pageNumber, size: sizeNumber };
    return this.http.get(url, { params });
  }

  public get(id: number) {
    return this.http.get<T>(`${this.baseUrl}/${this.entityUrl}/${id}`);
  }

  public getCustomUrl(url: string) {
    return this.http.get<any>(url);
  }

  public post(entity: T) {
    return this.http.post<T>(`${this.baseUrl}/${this.entityUrl}`, entity);
  }

  public postCustomUrl(url: string, entity?: any) {
    return this.http.post<any>(url, entity);
  }

  public put(entity: Entity) {
    return this.http.put<T>(
      `${this.baseUrl}/${this.entityUrl}/${entity.id}`,
      entity
    );
  }

  public putCustomUrl(url: string, entity?: any) {
    return this.http.put<any>(url, entity);
  }

  public delete(entity: Entity) {
    return this.http.delete<T>(
      `${this.baseUrl}/${this.entityUrl}/${entity.id}`
    );
  }

  public deleteCustomUrl(url: string) {
    return this.http.delete<any>(url);
  }
}
