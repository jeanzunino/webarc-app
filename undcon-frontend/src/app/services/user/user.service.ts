import { Injectable } from '@angular/core';
import { UrlService } from '../url/url.service';
import { User } from '@app/models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private urlService: UrlService) { }

  getUsers(): Observable<User[]> {
    return this.urlService.get('users');
  }
}
