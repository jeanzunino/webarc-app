import { Injectable } from '@angular/core';
import { UserDetail } from '@models/user/user-detail';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  private keys = {
    user: 'nin.user'
  }

  public setUser(user: UserDetail) {
    localStorage.setItem(this.keys.user, JSON.stringify(user));
  }

  public getUser(): UserDetail {
    return JSON.parse(localStorage.getItem(this.keys.user));
  }
 
  public clear() {
    localStorage.clear();
  }
}
