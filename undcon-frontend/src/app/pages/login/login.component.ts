import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '@services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  
  loginGroup: FormGroup;

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
    this.authService.signin(this.loginGroup.value)
  }
}
