import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Permission } from '@model/permission';
import { PermissionService } from '@service/permission/permission.service';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';

@Component({
  selector: 'app-permission-edit',
  templateUrl: './permission-edit.component.html',
  styleUrls: ['./permission-edit.component.scss']
})
export class PermissionEditComponent extends DefaultEditViewComponent<Permission> {

  constructor(public permissionModalRef: MDBModalRef,
              modalOptions: ModalOptions,
              toastr: ToastrService,
              translate: TranslateService,
              service: PermissionService) {
                super(permissionModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup(){
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required)
    });
  }

  onLoadValuesEdit(item: Permission){
      this.getFormGroup().patchValue({
        id: item.id,
        name: item.name
    });
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

}
