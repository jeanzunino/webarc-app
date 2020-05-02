import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { NgxSpinnerService } from 'ngx-spinner';

import { UserService } from '@service/user/user.service';
import { getTranslate } from '@shared/utils/utils'
import { EmployeeService } from '@service/employee/employee.service';
import { Page } from '@model/page';
import { Employee } from '@model/employee';
import { PermissionService } from '@service/permission/permission.service';
import { Permission } from '@model/permission';
import { Modal } from '@shared/model/modal';
import { StorageService } from '@service/storage/storage.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {

  userFormGroup: FormGroup;
  employees: Employee[];
  permissions: Permission[];
  data: Modal;
  tenant;

  constructor(public userModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private userService: UserService,
              private employeeService: EmployeeService,
              private permissionService: PermissionService,
              private spinner: NgxSpinnerService,
              private storageService: StorageService) { }

  ngOnInit() {
    this.userFormGroup = new FormGroup({
      id: new FormControl(null),
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      confirmPassword: new FormControl('', Validators.required),
      employee: new FormControl(null, Validators.required),
      permission: new FormControl(null, Validators.required),
      active: new FormControl(false)
    });
    this.onLoadValues();
    this.spinner.hide()
  }

  async onLoadValues() {
    this.data = this.modalOptions.data as Modal;
    if (this.data.content) {
      const user = this.data.content;
      this.userFormGroup.patchValue({
        id: user.id,
        login: user.login,
        employee: user.employee.id,
        permission: user.permission.id,
        active: user.active
      });
      this.passwordForm.clearValidators();
      this.confirmPasswordForm.clearValidators();
    }
    this.employees = (await this.employeeService.getAll().toPromise() as Page<Employee>).content;
    this.permissions = (await this.permissionService.getAll().toPromise() as Page<Permission>).content;
    this.tenant = this.storageService.getUser().tenant;
  }

  onSave() {
    if (this.validForm()) {
      this.employeeForm.setValue(this.employees.find(employee => employee.id === parseInt(this.employeeForm.value)));
      this.permissionForm.setValue(this.permissions.find(permission => permission.id === parseInt(this.permissionForm.value)));
      if (this.data.content) {
        this.userService.put(this.userFormGroup.value, parseInt(this.userFormGroup.get('id').value)).toPromise()
        .then(teste => {
          console.log(teste)
          this.userModalRef.hide()
        });
      } else {
        this.userService.post(this.userFormGroup.value).toPromise()
        .then(teste => {
          console.log(teste)
        });
      }
    }
  }

  private validForm() {
    if (this.passwordForm.value !== this.confirmPasswordForm.value) {
      this.toastr.error(getTranslate('error.authentication.message'),
                        getTranslate('error.save.title', {entity: 'BATATA'}));
      return false;
    }
    return true;
  }

  get loginForm() {
    return this.userFormGroup.get('login');
  }

  get passwordForm() {
    return this.userFormGroup.get('password');
  }

  get confirmPasswordForm() {
    return this.userFormGroup.get('confirmPassword');
  }

  get employeeForm() {
    return this.userFormGroup.get('employee');
  }

  get permissionForm() {
    return this.userFormGroup.get('permission');
  }
}
