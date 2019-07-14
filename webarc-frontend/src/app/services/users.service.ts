import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { User } from '../model/user';
import { LIST } from '../mocks/mock-users';
import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private messageService: MessageService) { }

  getAll(): Observable<User[]> {
    // TODO: send the message _after_ fetching the heroes
    this.messageService.add('PeopleService: fetched user');
    return of(LIST);
  }

  get(id: number): Observable<User> {
    // TODO: send the message _after_ fetching the hero
    this.messageService.add(`PeopleService: fetched user id=${id}`);
    return of(LIST.find(hero => hero.id === id));
  }
}
