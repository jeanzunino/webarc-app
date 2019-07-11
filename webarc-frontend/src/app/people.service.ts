import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { Person } from './model/person';
import { LIST } from './mocks/mock-people';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  constructor(private messageService: MessageService) { }

  getAll(): Observable<Person[]> {
    // TODO: send the message _after_ fetching the heroes
    this.messageService.add('PeopleService: fetched people');
    return of(LIST);
  }

  get(id: number): Observable<Person> {
    // TODO: send the message _after_ fetching the hero
    this.messageService.add(`PeopleService: fetched person id=${id}`);
    return of(LIST.find(hero => hero.id === id));
  }
}
