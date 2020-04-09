import { Injectable } from '@angular/core';

import { Purchase } from '@model/purchase';
import { PurchaseService } from '@service/purchase/purchase.service';
import { GenericListResolver } from '@component/generic-list/generic-list.resolver';


@Injectable()
export class PurchaseResolver extends GenericListResolver<Purchase> {
  constructor(service: PurchaseService) {
    super(service)
  }
}