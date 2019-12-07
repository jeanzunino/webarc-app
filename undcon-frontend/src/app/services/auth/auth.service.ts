import { Injectable } from '@angular/core';
import { User } from '@models/user/user';
import { EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { UrlService } from '@services/url/url.service';
import { StorageService } from '@services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  displayMenuEmitter = new EventEmitter<boolean>();

  constructor(
    private router: Router,
    private urlService: UrlService,
    private storageService: StorageService
  ) { }

  signin(user: User) {
    this.urlService.post('login', user).subscribe(userDetail => {
      if (userDetail.token) {
        this.storageService.setUser(userDetail);
        this.displayMenuEmitter.emit(true);
        this.router.navigate(['/home'])
      }
    });
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
