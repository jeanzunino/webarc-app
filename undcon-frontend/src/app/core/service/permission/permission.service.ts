
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Permission } from '@core/model/permission';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class PermissionService extends EntityService<Permission> {

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'permissions')
  }

  getPermissions(): Observable<Permission[]> {
    return this.getAll();
  }
}
