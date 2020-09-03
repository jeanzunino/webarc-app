import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

import { Permission } from "@model/permission";
import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";
import { Page } from '@model/page';
import { PermissionItem } from '@app/core/model/permission-item';

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

  getPermissionItems(permissionId: number, page: number): Observable<Page<PermissionItem>> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/${permissionId}/itens`, null, page) as Observable<Page<PermissionItem>>;
  }

  addPermissionItem(permissionId: number, item: PermissionItem): Observable<any> {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${permissionId}/itens`, item);
  }

  deletePermissionItem(permissionId: number, itemId: number): Observable<any> {
    return this.deleteCustomUrl(`${this.baseUrl}/${this.entityUrl}/${permissionId}/itens/${itemId}`);
  }
}
