import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ProductsService } from '../../services/products.service';
import { Product } from '../../model/product';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  @Input() item: Product;

  constructor(
    private route: ActivatedRoute,
    private service: ProductsService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.get();
  }

  get(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.service.get(id)
      .subscribe(item => this.item = item);
  }

  goBack(): void {
    this.location.back();
  }

}
