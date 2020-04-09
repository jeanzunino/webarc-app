import { Component, OnInit } from '@angular/core';

import { AuthService } from '@service/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private authService: AuthService) { }

  visibleSidebar1;

    ngOnInit() {
        
    }

    logout() {
      this.authService.signout();
    }
}