import { Injectable } from '@angular/core';

import { Product } from '@model/product';
import { ProductService } from '@service/product/product.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';


@Injectable()
export class ProductResolver extends GetAllResolver<Product> {
  constructor(private service: ProductService) {
    super(service)
  }
}