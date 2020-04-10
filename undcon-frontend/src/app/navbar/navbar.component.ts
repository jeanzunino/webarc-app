import { Component } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { TranslateService } from '@ngx-translate/core';

import { AuthService } from '@service/auth/auth.service';
import { AppInjector } from '@app/app.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  private spinner = AppInjector.get(NgxSpinnerService);

  constructor(private authService: AuthService,
              public translate: TranslateService) { }

  loadingPage() {
    this.spinner.show();
  }

  logout() {
    this.authService.signout();
  }
}