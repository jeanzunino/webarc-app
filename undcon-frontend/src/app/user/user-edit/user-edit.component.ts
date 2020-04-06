import { Component, OnInit, AfterViewInit, AfterContentInit, OnChanges, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { User } from '@app/core/model/user';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from '@app/core/service/user/user.service';

export class Teste {
  user: User
}

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {

  userFormGroup: FormGroup;

  constructor(public modalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: UserService) {
            
  }

  ngOnInit() {
    this.userFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      confirmPassword: new FormControl('', Validators.required)
    });
    let dados = this.modalOptions.data as Teste
    if (dados.user) {
      this.nameForm.setValue(dados.user.login) 
    }
    
  }

  get nameForm() { 
    return this.userFormGroup.get('name'); 
  }

  get passwordForm() {
    return this.userFormGroup.get('password'); 
  }

  get confirmPasswordForm() {
    return this.userFormGroup.get('confirmPassword'); 
  }

  private validForm() {
    if (this.passwordForm.value !== this.confirmPasswordForm.value) {
      this.toastr.error(this.getTranslate('error.authentication.message'),
                        this.getTranslate('error.save.title', {entity: 'BATATA'}));
      return false;
    }
    return true;
  }

  onSave() {
    if (this.validForm()) {
      alert(this.modalRef.content.user.login) 
    }
  }

  private getTranslate(key, params = null) {
    if (params) {
      return this.translate.instant(key, params)
    } else {
      return this.translate.instant(key);
    }
  }
}
