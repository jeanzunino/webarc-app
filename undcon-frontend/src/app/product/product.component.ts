import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { ProductService } from '@service/product/product.service';
import { Product } from '@model/product';
import { GenericListComponent } from '@app/core/component/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: './product.component.html',
  styleUrls: ['../core/component/generic-list.component.scss']
})
export class ProductComponent extends GenericListComponent<Product>  {

  constructor(private service: ProductService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
