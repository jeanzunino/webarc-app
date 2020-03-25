import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { ProductService } from '@service/product/product.service';
import { Product } from '@model/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private productService: ProductService) { }

  items: Product[];

  ngOnInit() {
    this.productService.getProducts()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(items => {
      this.items = items;
    });
  }

  onClickItem(item) {

  }
}
