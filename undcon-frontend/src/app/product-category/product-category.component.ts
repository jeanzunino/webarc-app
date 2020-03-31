import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { ProductCategoryService } from '@service/product-category/product-category.service';
import { ProductCategory } from '@model/product-category';
import { GenericListComponent } from '@component-generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../core/component/generic-list/generic-list.component.html',
  styleUrls: ['../core/component/generic-list/generic-list.component.scss']
})
export class ProductCategoryComponent extends GenericListComponent<ProductCategory>  {

  constructor(private service: ProductCategoryService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
