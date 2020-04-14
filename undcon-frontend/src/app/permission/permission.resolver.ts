import { Injectable } from '@angular/core';

import { Permission } from '@model/permission';
import { PermissionService } from '@service/permission/permission.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';


@Injectable()
export class PermissionResolver extends GetAllResolver<Permission> {
  constructor(service: PermissionService) {
    super(service)
  }
}