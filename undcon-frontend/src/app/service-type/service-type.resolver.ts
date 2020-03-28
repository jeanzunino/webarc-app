import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { ServiceType } from '@app/core/model/service-type';
import { ServiceTypeService } from '@app/core/service/service-type/service-type.service';
import { GenericListResolver } from '@app/core/component/generic-list.resolver';


@Injectable()
export class ServiceTypeResolver extends GenericListResolver<ServiceType> {
  constructor(private service: ServiceTypeService) {
    super(service)
  }
}
