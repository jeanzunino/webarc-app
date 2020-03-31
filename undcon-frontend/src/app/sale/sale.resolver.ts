import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Sale } from '@app/core/model/sale';
import { SaleService } from '@app/core/service/sale/sale.service';
import { GenericListResolver } from '@component-generic-list/generic-list.resolver';


@Injectable()
export class SaleResolver extends GenericListResolver<Sale> {
  constructor(private service: SaleService) {
    super(service)
  }
}
