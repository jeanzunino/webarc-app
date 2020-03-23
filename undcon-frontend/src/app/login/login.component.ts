import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '@service/auth/auth.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private ngUnsubscribe = new Subject();
  loginGroup: FormGroup;
  hasError;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
    if (this.authService.getAuthenticatedUser()) {
      this.router.navigate(['/home'])
    }

    this.loginGroup = this.formBuilder.group({
      login: ['', Validators.compose([Validators.email, Validators.required])],
      password: ['', Validators.required]
    })
  }

  get f() { 
    return this.loginGroup.controls; 
  }

  get loginForm() { 
    return this.loginGroup.get('login'); 
  }

  get passwordForm() {
    return this.loginGroup.get('password'); 
  }

  signin() {
    this.authService.signinValidate(this.loginGroup.value)
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe(
        userDetail => {
          if (userDetail.token) {
            this.authService.setValuesAfterSigninValidate(userDetail);
            this.router.navigate(['/home'])
          }
        },
        error => {
          this.hasError = true
        }
      );
  }

  @ViewChild('alert', { static: true }) alert: ElementRef;

  openAlert() {
    this.alert.nativeElement.classList.add('show');
  }

  closeAlert() {
    this.alert.nativeElement.classList.remove('show');
  }
}
