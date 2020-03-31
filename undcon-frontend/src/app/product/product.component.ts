import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { ProductService } from '@service/product/product.service';
import { Product } from '@model/product';
import { GenericListComponent } from '@component-generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../core/component/generic-list/generic-list.component.html',
  styleUrls: ['../core/component/generic-list/generic-list.component.scss']
})
export class ProductComponent extends GenericListComponent<Product>  {

  constructor(private service: ProductService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
