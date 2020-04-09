import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ProductService } from '@service/product/product.service';
import { Product } from '@model/product';
import { GenericListComponent } from '@component/generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class ProductComponent extends GenericListComponent<Product>  {

  constructor(service: ProductService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}