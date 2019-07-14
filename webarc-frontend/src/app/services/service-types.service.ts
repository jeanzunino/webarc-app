import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { Service } from '../model/service';
import { LIST } from '../mocks/mock-services';
import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceTypesService {

  constructor(private messageService: MessageService) { }

  getAll(): Observable<Service[]> {
    // TODO: send the message _after_ fetching the heroes
    this.messageService.add('PeopleService: fetched product');
    return of(LIST);
  }

  get(id: number): Observable<Service> {
    // TODO: send the message _after_ fetching the hero
    this.messageService.add(`PeopleService: fetched product id=${id}`);
    return of(LIST.find(hero => hero.id === id));
  }
}
