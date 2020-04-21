import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Product } from '@model/product';
import { ProductService } from '@service/product/product.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html'
})
export class ProductComponent extends GridViewComponent<Product> {

  tableValues = new Table().set('id', 'product.id').set('name', 'product.name').set('unit', 'product.unit')
                           .set('purchasePrice', 'product.purchasePrice').set('salePrice', 'product.salePrice')
                           .set('stock', 'product.stock').set('stockMin', 'product.stockMin').get();

  constructor(service: ProductService,
              activatedRoute: ActivatedRoute) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}