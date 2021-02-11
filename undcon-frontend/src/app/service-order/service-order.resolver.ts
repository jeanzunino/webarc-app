import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';

import { ServiceOrder } from '@app/core/model/service-order';
import { ServiceOrderService } from '@app/core/service/service-order/service-order.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';

@Injectable()
export class ServiceOrderResolver extends GetAllResolver<ServiceOrder> {
  constructor(private service: ServiceOrderService) {
    super(service);
  }
}

@Injectable()
export class ServiceOrderDetailResolver {
  constructor(private saleService: ServiceOrderService) {}

    public resolve(route: ActivatedRouteSnapshot) {
      if (route.params.id === 'new') {
        return;
      }
      return this.saleService.get(route.params.id);
    }
}

