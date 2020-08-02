import { Injectable } from "@angular/core";

import { Purchase } from "@model/purchase";
import { PurchaseService } from "@service/purchase/purchase.service";
import { GetAllResolver } from "@shared/resolver/generic.resolver";

@Injectable()
export class PurchaseResolver extends GetAllResolver<Purchase> {
  constructor(service: PurchaseService) {
    super(service);
  }
}
