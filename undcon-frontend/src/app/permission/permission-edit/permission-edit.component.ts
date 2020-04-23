import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { PermissionService } from '@service/permission/permission.service';
import { Modal } from '@shared/model/modal';

@Component({
  selector: 'app-permission-edit',
  templateUrl: './permission-edit.component.html',
  styleUrls: ['./permission-edit.component.scss']
})
export class PermissionEditComponent implements OnInit {

  permissionFormGroup: FormGroup;
  data: Modal;

  constructor(public permissionModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: PermissionService) {
  }

  ngOnInit() {
    this.permissionFormGroup = new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required)
    });
    this.onLoadValues();
  }

  async onLoadValues() {
    this.data = this.modalOptions.data as Modal;
    if (this.data.content) {
      const permission = this.data.content;
      this.permissionFormGroup.patchValue({
        id: permission.id,
        name: permission.name
      });
    } else{
      this.permissionFormGroup.patchValue({
        id: null,
        name: null

      });
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
      if (!this.data.content) {
        this.service.post(this.permissionFormGroup.value).toPromise()
        .then(teste => {
          console.log(teste)
        });
      } else {
        this.service.put(this.permissionFormGroup.value, parseInt(this.permissionFormGroup.get('id').value)).toPromise()
        .then(teste => {
          console.log(teste)
        });
      }
    }
  }
}
