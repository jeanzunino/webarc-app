import { Injectable } from "@angular/core";
import { Resolve } from '@angular/router';

import { Permission } from "@model/permission";
import { PermissionService } from "@service/permission/permission.service";
import { GetAllResolver } from "@shared/resolver/generic.resolver";
import { ResourceTypeEnum } from '@enum/resource-type-enum';

@Injectable()
export class PermissionResolver extends GetAllResolver<Permission> {
  constructor(service: PermissionService) {
    super(service);
  }
}
