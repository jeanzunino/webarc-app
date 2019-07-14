import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { Permission } from '../model/permission';
import { LIST } from '../mocks/mock-permissions';
import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root'
})
export class PermissionsService {

  constructor(private messageService: MessageService) { }

  getAll(): Observable<Permission[]> {
    // TODO: send the message _after_ fetching the heroes
    this.messageService.add('PermissionsService: fetched Permission');
    return of(LIST);
  }

  get(id: number): Observable<Permission> {
    // TODO: send the message _after_ fetching the hero
    this.messageService.add(`PermissionsService: fetched Permission id=${id}`);
    return of(LIST.find(hero => hero.id === id));
  }
}
