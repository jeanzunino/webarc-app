import { Injectable } from '@angular/core';

import { Tenant } from '@model/tenant';
import { TenantService } from '@service/tenant/tenant.service';
import { GenericListResolver } from '@component/generic-list/generic-list.resolver';


@Injectable()
export class TenantResolver extends GenericListResolver<Tenant> {
  constructor(service: TenantService) {
    super(service)
  }
}