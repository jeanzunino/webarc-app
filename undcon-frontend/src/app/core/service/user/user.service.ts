import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '@model/user';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';
import { ResourceTypeEnum } from '@app/core/enum/resource-type-enum';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService extends EntityService<User> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'users');
  }

  getPermissionOfLoggeduser(): Observable<Array<ResourceTypeEnum>> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/current/permissions/`, null, 100) as Observable<
      Array<ResourceTypeEnum>
    >;
  }
}
