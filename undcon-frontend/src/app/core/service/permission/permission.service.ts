import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

import { Permission } from "@model/permission";
import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";
import { Page } from '@model/page';
import { PermissionItem } from '@app/core/model/permission-item';
import { ResourceTypeEnum } from '@enum/resource-type-enum';

@Injectable({
  providedIn: "root",
})
export class PermissionService extends EntityService<Permission> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, "permissions");
  }

  getPermissionItems(permissionId: number): Observable<PermissionItem[]> {
    return this.http.get<PermissionItem[]>(`${this.baseUrl}/${this.entityUrl}/${permissionId}/itens`);
  }

  getPermissionItemsResource
  (): Observable<ResourceTypeEnum[]> {
    return this.http.get<ResourceTypeEnum[]>(`${this.baseUrl}/${this.entityUrl}/resources`);
  }

  addPermissionItem(permissionId: number, item: PermissionItem): Observable<any> {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${permissionId}/itens`, item);
  }

  deletePermissionItem(permissionId: number, res: ResourceTypeEnum): Observable<any> {
    return this.deleteCustomUrl(`${this.baseUrl}/${this.entityUrl}/${permissionId}/itens/${res}`);
  }
}
