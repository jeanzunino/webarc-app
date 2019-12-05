import { Injectable } from '@angular/core';
import { User } from '../../models/user';
import { EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { UrlService } from '../url/url.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authenticatedUser: boolean = false;

  displayMenuEmitter = new EventEmitter<boolean>();

  constructor(
    private router: Router,
    private urlService: UrlService
  ) { }

  signin(user: User) {
    var teste = this.urlService.post('login', user).subscribe(a => {
      console.log(a);
    });
    if (user.login === 'a@teste' && user.password === '123') {
      this.authenticatedUser = true;
      this.displayMenuEmitter.emit(true);
      this.router.navigate(['/home'])
    } else {
      this.displayMenuEmitter.emit(true);
    }
  }

  getAuthenticatedUser() {
    return this.authenticatedUser;
  }
}
