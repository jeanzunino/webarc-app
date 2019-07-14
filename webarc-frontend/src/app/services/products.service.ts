import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { Product } from '../model/product';
import { LIST } from '../mocks/mock-products';
import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private messageService: MessageService) { }

  getAll(): Observable<Product[]> {
    // TODO: send the message _after_ fetching the heroes
    this.messageService.add('PeopleService: fetched product');
    return of(LIST);
  }

  get(id: number): Observable<Product> {
    // TODO: send the message _after_ fetching the hero
    this.messageService.add(`PeopleService: fetched product id=${id}`);
    return of(LIST.find(hero => hero.id === id));
  }
}
