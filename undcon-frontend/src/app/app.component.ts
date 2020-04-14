import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { AuthService } from '@service/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  displayMenu: boolean = false;
  
  constructor(private authService: AuthService,
              public translate: TranslateService) {
  }

  ngOnInit() {
    this.authService.displayMenuEmitter.subscribe(
      display => this.displayMenu = display
    );
  }
}