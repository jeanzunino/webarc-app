import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { ProductCategory } from '@app/core/model/product-category';
import { ProductCategoryService } from '@app/core/service/product-category/product-category.service';
import { GenericListResolver } from '@component-generic-list/generic-list.resolver';


@Injectable()
export class ProductCategoryResolver extends GenericListResolver<ProductCategory> {
  constructor(private service: ProductCategoryService) {
    super(service)
  }
}
