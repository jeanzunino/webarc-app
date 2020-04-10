import { Component, Injector } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { AuthService } from '@service/auth/auth.service';

export let AppInjector: Injector;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  displayMenu: boolean = false;
  
  constructor(private authService: AuthService,
              private translate: TranslateService,
              private injector: Injector) { 
    this.translate.setDefaultLang('pt-BR');
    AppInjector = this.injector
  }

  ngOnInit() {
    this.authService.displayMenuEmitter.subscribe(
      display => this.displayMenu = display
    );
  }
}