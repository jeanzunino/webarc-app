import { Injectable } from "@angular/core";

import { LoginUser } from "@model/login-user";

@Injectable({
  providedIn: "root",
})
export class StorageService {
  constructor() {}

  private keys = {
    user: "nin.user",
  };

  public setUser(login: LoginUser) {
    localStorage.setItem(this.keys.user, JSON.stringify(login));
  }

  public getUser(): LoginUser {
    return JSON.parse(localStorage.getItem(this.keys.user));
  }

  public clear() {
    localStorage.clear();
  }
}
