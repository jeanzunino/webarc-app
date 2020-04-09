import { Injectable } from '@angular/core';

import { ServiceType } from '@model/service-type';
import { ServiceTypeService } from '@service/service-type/service-type.service';
import { GenericListResolver } from '@component/generic-list/generic-list.resolver';


@Injectable()
export class ServiceTypeResolver extends GenericListResolver<ServiceType> {
  constructor(private service: ServiceTypeService) {
    super(service)
  }
}