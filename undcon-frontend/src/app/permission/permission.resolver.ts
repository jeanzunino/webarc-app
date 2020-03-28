import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Permission } from '@app/core/model/permission';
import { PermissionService } from '@app/core/service/permission/permission.service';
import { GenericListResolver } from '@app/core/component/generic-list.resolver';


@Injectable()
export class PermissionResolver extends GenericListResolver<Permission> {
  constructor(private service: PermissionService) {
    super(service)
  }
}
