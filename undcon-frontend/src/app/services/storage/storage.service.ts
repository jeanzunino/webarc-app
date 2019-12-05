import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  private keys = {
    token: 'nin.token'
  }

  public setToken(token: string) {
    localStorage.setItem(this.keys.token, token);
  }

  public getToken() {
    localStorage.getItem(this.keys.token);
  }
}
