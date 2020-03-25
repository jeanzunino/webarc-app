import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { ProductCategoryService } from '@service/product-category/product-category.service';
import { ProductCategory } from '@model/product-category';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html',
  styleUrls: ['./product-category.component.scss']
})
export class ProductCategoryComponent implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private productCategoryService: ProductCategoryService) { }

  items: ProductCategory[];

  ngOnInit() {
    this.productCategoryService.getProductCategories()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(items=> {
      this.items= items;
    });
  }

  onClickItem(item) {
  }
}
