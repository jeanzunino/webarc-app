import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { RouterEvent, NavigationStart, NavigationEnd, NavigationCancel, NavigationError, Router } from '@angular/router';

import { AuthService } from '@service/auth/auth.service';
import { SharedInjector } from '@shared/shared.module';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {

  private spinner = SharedInjector.get(NgxSpinnerService);
  displayMenu = false;

  constructor(
    private authService: AuthService,
    public translate: TranslateService,
    private router: Router
  ) {}

  ngOnInit() {
    this.authService.displayMenuEmitter.subscribe(
      display => (this.displayMenu = display)
    );

    this.router.events.subscribe((event: RouterEvent) => {
      if (event instanceof NavigationStart) {
          this.spinner.show();
      } else if (event instanceof NavigationEnd || event instanceof NavigationCancel || event instanceof NavigationError) {
          this.spinner.hide();
      }
  });
  }
}
