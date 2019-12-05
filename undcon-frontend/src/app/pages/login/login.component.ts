import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { User } from '../../models/user';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  group: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService) { }

  ngOnInit() {
    this.group = this.fb.group({
      login: ['', Validators.email],
      password: ['', Validators.required]
    })
  }

  get f() { 
    return this.group.controls; 
  }

  signin() {
    this.authService.signin(this.group.value)
  }
}
