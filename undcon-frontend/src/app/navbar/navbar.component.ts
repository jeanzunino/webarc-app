import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { AuthService } from '@service/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {

  constructor(
    private authService: AuthService,
    public translate: TranslateService
  ) {}

  logout() {
    this.authService.signout();
  }
}
