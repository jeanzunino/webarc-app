import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ProductCategoryService } from '@service/product-category/product-category.service';
import { ProductCategory } from '@model/product-category';
import { GenericListComponent } from '@component/generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class ProductCategoryComponent extends GenericListComponent<ProductCategory>  {

  constructor(service: ProductCategoryService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}