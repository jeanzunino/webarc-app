import { Injectable } from '@angular/core';

import { Sale } from '@model/sale';
import { SaleService } from '@service/sale/sale.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';
import { ActivatedRouteSnapshot } from '@angular/router';

@Injectable()
export class SaleResolver extends GetAllResolver<Sale> {
  constructor(service: SaleService) {
    super(service);
  }
}

@Injectable()
export class SaleDetailResolver {
  constructor(private saleService: SaleService) {}

    public resolve(route: ActivatedRouteSnapshot) {
        if (route.params.id === 'new') {
          return;
        }
        return this.saleService.get(route.params.id);
    }
}

@Injectable()
export class SaleItensResolver {
  constructor(private saleService: SaleService) {}

    public resolve(route: ActivatedRouteSnapshot) {
        if (route.params.id === 'new') {
          return;
        }
        return this.saleService.getSaleItens(route.params.id, 0);
    }
}
