import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { NgxSpinnerService } from 'ngx-spinner';

import { User } from '@model/user';
import { Employee } from '@model/employee';
import { Permission } from '@model/permission';
import { Page } from '@model/page';
import { getTranslate } from '@shared/utils/utils'
import { EmployeeService } from '@service/employee/employee.service';
import { UserService } from '@service/user/user.service';
import { PermissionService } from '@service/permission/permission.service';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';
import { Modal } from '@shared/model/modal';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent extends DefaultEditViewComponent<User> {

  employees: Employee[];
  permissions: Permission[];

  constructor(public userModalRef: MDBModalRef,
    modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    userService: UserService,
    private employeeService: EmployeeService,
    private permissionService: PermissionService,
    spinner: NgxSpinnerService) {
      super(userModalRef, modalOptions, toastr, translate, userService);
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      confirmPassword: new FormControl('', Validators.required),
      employee: new FormControl(null, Validators.required),
      permission: new FormControl(null, Validators.required),
      active: new FormControl(false)
    });
  }

  onLoadValuesEdit(user: User) {
    this.getFormGroup().patchValue({
      id: user.id,
      login: user.login,
      employee: user.employee.id,
      permission: user.permission.id,
      active: user.active
    });
    this.passwordForm.clearValidators();
    this.confirmPasswordForm.clearValidators();
  }

  async onLoadData() {
    this.employees = (await this.employeeService.getAll().toPromise() as Page<Employee>).content;
    this.permissions = (await this.permissionService.getAll().toPromise() as Page<Permission>).content;
  }

  validForm() {
    if (this.passwordForm.value !== this.confirmPasswordForm.value) {
      this.toastr.error(getTranslate('error.authentication.message'),
        getTranslate('error.save.title', { entity: 'BATATA' }));
      return false;
    }
    return true;
  }

  afterValidateFormSave(){
    this.employeeForm.setValue(this.employees.find(employee => employee.id == this.employeeForm.value));
    this.permissionForm.setValue(this.permissions.find(permission => permission.id == this.permissionForm.value));
  }

  get loginForm() {
    return this.getFormGroup().get('login');
  }

  get passwordForm() {
    return this.getFormGroup().get('password');
  }

  get confirmPasswordForm() {
    return this.getFormGroup().get('confirmPassword');
  }

  get employeeForm() {
    return this.getFormGroup().get('employee');
  }

  get permissionForm() {
    return this.getFormGroup().get('permission');
  }

}
