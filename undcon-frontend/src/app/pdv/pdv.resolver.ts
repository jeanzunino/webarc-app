import { Injectable } from '@angular/core';

import { Sale } from '@model/sale';
import { SaleService } from '@service/sale/sale.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';


@Injectable()
export class PdvResolver extends GetAllResolver<Sale> {
  constructor(private service: SaleService) {
    super(service)
  }
}
