import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Employee } from '@model/employee';
import { EmployeeService } from '@service/employee/employee.service';

export class Teste {
  employee: Employee
}

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.scss']
})
export class EmployeeEditComponent implements OnInit {

  employeeFormGroup: FormGroup;

  constructor(public employeeModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: EmployeeService) {
  }

  ngOnInit() {
    this.employeeFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      phone: new FormControl('')
    });
    let dados = this.modalOptions.data as Teste
    if (dados.employee) {
      this.nameForm.setValue(dados.employee.name);
      this.phoneForm.setValue(dados.employee.phone);
    }

  }

  get nameForm() {
    return this.employeeFormGroup.get('name');
  }

  get phoneForm() {
    return this.employeeFormGroup.get('phone');
  }

  private validForm() {
    return true;
  }


  onSave() {
    if (this.validForm()) {
      alert(this.nameForm.value)
    }
  }
}
