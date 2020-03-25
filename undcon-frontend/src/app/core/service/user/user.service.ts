import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { User } from '@model/user';
import { EntityService } from '@service/entity/entity.service';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserService extends EntityService<User> {
  
  constructor(protected http: HttpClient,
              protected storageService: StorageService) {
    super(http, storageService, 'users')
  }

  getUsers(): Observable<User[]> {
    return this.getAll();
  }
}