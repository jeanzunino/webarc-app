import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Permission } from '@model/permission';
import { PermissionService } from '@service/permission/permission.service';

export class Teste {
  permission: Permission
}

@Component({
  selector: 'app-permission-edit',
  templateUrl: './permission-edit.component.html',
  styleUrls: ['./permission-edit.component.scss']
})
export class PermissionEditComponent implements OnInit {

  permissionFormGroup: FormGroup;

  constructor(public permissionModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: PermissionService) {
  }

  ngOnInit() {
    this.permissionFormGroup = new FormGroup({
      name: new FormControl('', Validators.required)
    });
    let dados = this.modalOptions.data as Teste
    if (dados.permission) {
      this.nameForm.setValue(dados.permission.name);
    }

  }

  get nameForm() {
    return this.permissionFormGroup.get('name');
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
