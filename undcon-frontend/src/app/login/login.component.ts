import { UserService } from '@service/user/user.service';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { AuthService } from '@service/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  private ngUnsubscribe = new Subject();
  loginGroup: FormGroup;
  resetGroup: FormGroup;
  public resetPassword = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService,
    private translate: TranslateService,
    private userService: UserService
  ) {}

  ngOnInit() {
    if (this.authService.getAuthenticatedUser()) {
      this.router.navigate(['/home']);
    }

    this.loginGroup = this.formBuilder.group({
      login: ['', Validators.compose([Validators.email, Validators.required])],
      password: ['', Validators.required],
    });

    this.resetGroup = this.formBuilder.group({
      token: ['', Validators.required],
      login: ['', Validators.compose([Validators.email, Validators.required])],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  get loginForm() {
    return this.loginGroup.get('login');
  }

  get passwordForm() {
    return this.loginGroup.get('password');
  }

  get resetTokenForm() {
    return this.resetGroup.get('token');
  }

  get resetLoginForm() {
    return this.resetGroup.get('login');
  }

  get resetPasswordForm() {
    return this.resetGroup.get('password');
  }

  get resetConfirmPasswordForm() {
    return this.resetGroup.get('confirmPassword');
  }

  signin() {
    this.authService
      .signinValidate(this.loginGroup.value)
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe(
        (userDetail) => {
          if (userDetail.token) {
            this.authService.setValuesAfterSigninValidate(userDetail);
            this.router.navigate(['/home']);
          }
        },
        (error) => {
          if (error.status === 401)
            this.toastr.error(
              this.translate.instant(error.error.message),
              this.translate.instant('error.authentication.title')
            );
        }
      );
  }

  private passAndConfirmValido() {
    return this.resetPasswordForm.value === this.resetConfirmPasswordForm.value;
  }

  @ViewChild('alert', { static: true }) alert: ElementRef;

  openAlert() {
    this.alert.nativeElement.classList.add('show');
  }

  closeAlert() {
    this.alert.nativeElement.classList.remove('show');
  }

  public onResetPassword() {
    if (this.passAndConfirmValido()) {
      this.userService.resetPassword(this.resetGroup.getRawValue()).then(() => {
        this.toastr.success(
          'Senha resetada com sucesso!',
          'Sucesso'
        );
        this.resetPassword = false;

        this.passwordForm.setValue('');
        document.getElementById('password').focus();
        
      });
    } else {
      this.toastr.error(
        this.translate.instant('A senha e a confirmação não são iguais.'),
        this.translate.instant('Senhas inválidas')
      );
    }
  }
}
