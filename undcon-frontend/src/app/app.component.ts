import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '@service/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  displayMenu: boolean = false;
  
  constructor(
    private authService: AuthService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.authService.displayMenuEmitter.subscribe(
      display => this.displayMenu = display
    );
  }

  showSuccess() {
    this.toastr.success('Hello world!', 'Toastr fun!');
  }
}
