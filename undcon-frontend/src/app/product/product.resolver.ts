import { Injectable } from '@angular/core';

import { Product } from '@model/product';
import { ProductService } from '@service/product/product.service';
import { GenericListResolver } from '@component/generic-list/generic-list.resolver';


@Injectable()
export class ProductResolver extends GenericListResolver<Product> {
  constructor(private service: ProductService) {
    super(service)
  }
}