import { HttpClient } from "@angular/common/http";

import { Entity } from "@model/entity";
import { environment } from "@environment/environment";
import { StorageService } from "@service/storage/storage.service";

export class EntityService<T> {
  public baseUrl = environment.url;

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService,
    protected entityUrl: string
  ) {}

  public getAll(params?: {}) {
    return this.http.get(`${this.baseUrl}/${this.entityUrl}`, { params });
  }

  public get() {
    return this.http.get<T>(`${this.baseUrl}/${this.entityUrl}`);
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
