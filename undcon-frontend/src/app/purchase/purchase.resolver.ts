import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Purchase } from '@app/core/model/purchase';
import { PurchaseService } from '@app/core/service/purchase/purchase.service';
import { GenericListResolver } from '@component-generic-list/generic-list.resolver';


@Injectable()
export class PurchaseResolver extends GenericListResolver<Purchase> {
  constructor(private service: PurchaseService) {
    super(service)
  }
}
