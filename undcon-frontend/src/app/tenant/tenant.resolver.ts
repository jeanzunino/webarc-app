import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Tenant } from '@app/core/model/tenant';
import { TenantService } from '@app/core/service/tenant/tenant.service';
import { GenericListResolver } from '@component-generic-list/generic-list.resolver';


@Injectable()
export class TenantResolver extends GenericListResolver<Tenant> {
  constructor(private service: TenantService) {
    super(service)
  }
}
