import { Component, OnInit } from '@angular/core';

import { ProductsService } from '../services/products.service';
import { Product } from '../model/product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  selected: Product;

  list: Product[];

  constructor(private service: ProductsService) { }

  ngOnInit() {
    this.getHeroes();
  }

  onSelect(item: Product): void {
    this.selected = item;
  }

  getHeroes(): void {
    this.service.getAll()
        .subscribe(heroes => this.list = heroes);
  }

}
