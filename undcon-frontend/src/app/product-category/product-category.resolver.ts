import { Injectable } from '@angular/core';

import { ProductCategory } from '@model/product-category';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';

@Injectable()
export class ProductCategoryResolver extends GetAllResolver<ProductCategory> {
  constructor(private service: ProductCategoryService) {
    super(service);
  }
}
