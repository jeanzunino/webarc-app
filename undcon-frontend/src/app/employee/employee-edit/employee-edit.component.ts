import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Employee } from '@model/employee';
import { EmployeeService } from '@service/employee/employee.service';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.scss']
})
export class EmployeeEditComponent extends DefaultEditViewComponent<Employee> {

  constructor(public employeeModalRef: MDBModalRef,
              modalOptions: ModalOptions,
              toastr: ToastrService,
              translate: TranslateService,
              service: EmployeeService) {
                super(employeeModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup(){
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      phone: new FormControl('')
    });
  }

  onLoadValuesEdit(employee: Employee){
      this.getFormGroup().patchValue({
        id: employee.id,
        name: employee.name,
        phone: employee.phone
    });
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

  get phoneForm() {
    return this.getFormGroup().get('phone');
  }

}
