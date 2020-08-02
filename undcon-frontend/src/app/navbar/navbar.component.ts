import { Component } from "@angular/core";
import { NgxSpinnerService } from "ngx-spinner";
import { TranslateService } from "@ngx-translate/core";

import { AuthService } from "@service/auth/auth.service";
import { SharedInjector } from "@app/shared/shared.module";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html"
})
export class NavbarComponent {
  private spinner = SharedInjector.get(NgxSpinnerService);

  constructor(
    private authService: AuthService,
    public translate: TranslateService
  ) {}

  loadingPage() {
    this.spinner.show();
  }

  logout() {
    this.authService.signout();
  }
}
