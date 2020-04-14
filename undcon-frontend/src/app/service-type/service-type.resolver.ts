import { Injectable } from '@angular/core';

import { ServiceType } from '@model/service-type';
import { ServiceTypeService } from '@service/service-type/service-type.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';


@Injectable()
export class ServiceTypeResolver extends GetAllResolver<ServiceType> {
  constructor(private service: ServiceTypeService) {
    super(service)
  }
}