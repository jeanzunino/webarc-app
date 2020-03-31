import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Product } from '@app/core/model/product';
import { ProductService } from '@app/core/service/product/product.service';
import { GenericListResolver } from '@component-generic-list/generic-list.resolver';


@Injectable()
export class ProductResolver extends GenericListResolver<Product> {
  constructor(private service: ProductService) {
    super(service)
  }
}
