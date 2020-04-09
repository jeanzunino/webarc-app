import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '@model/user';
import { StorageService } from '@service/storage/storage.service';
import { EntityService } from '@service/entity/entity.service';
import { LoginUser } from '@core/model/login-user';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends EntityService<LoginUser> {

  displayMenuEmitter = new EventEmitter<boolean>();

  constructor(
    private router: Router,
    protected storageService: StorageService,
    protected http: HttpClient,
  ) { 
    super(http, storageService, 'login')
  }

  signinValidate(user: User) {
    return this.signin(user);
  }

  setValuesAfterSigninValidate(userDetail: LoginUser) {
    this.storageService.setUser(userDetail);
    this.displayMenuEmitter.emit(true);
  }

  /**
   * 
   * Método específico para logar
   * 
   * @param user 
   */
  private signin(user: User): Observable<LoginUser> {
    return this.http.post<LoginUser>(`${this.baseUrl}/${this.entityUrl}`, JSON.stringify(user));
  }

  signout() {
    this.storageService.clear();
    this.router.navigate(['/login'])
    this.displayMenuEmitter.emit(false);
  }

  public getAuthenticatedUser() {
    const user = this.storageService.getUser();
    return user != null && user.token;
  }
}