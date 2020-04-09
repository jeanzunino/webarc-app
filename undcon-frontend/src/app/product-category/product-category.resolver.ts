import { Injectable } from '@angular/core';

import { ProductCategory } from '@model/product-category';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { GenericListResolver } from '@component/generic-list/generic-list.resolver';


@Injectable()
export class ProductCategoryResolver extends GenericListResolver<ProductCategory> {
  constructor(private service: ProductCategoryService) {
    super(service)
  }
}