import { Injectable } from '@angular/core';

import { Permission } from '@model/permission';
import { PermissionService } from '@service/permission/permission.service';
import { GenericListResolver } from '@component/generic-list/generic-list.resolver';


@Injectable()
export class PermissionResolver extends GenericListResolver<Permission> {
  constructor(service: PermissionService) {
    super(service)
  }
}