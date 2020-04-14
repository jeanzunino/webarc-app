import { Injectable } from '@angular/core';

import { Tenant } from '@model/tenant';
import { TenantService } from '@service/tenant/tenant.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';


@Injectable()
export class TenantResolver extends GetAllResolver<Tenant> {
  constructor(service: TenantService) {
    super(service)
  }
}