import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Tenant } from '@model/tenant';
import { TenantService } from '@service/tenant/tenant.service';
import { DefaultEditViewComponent } from '@shared/component/default-edit-view/default-edit-view.component';

@Component({
  selector: 'app-tenant-edit',
  templateUrl: './tenant-edit.component.html'
})
export class TenantEditComponent extends DefaultEditViewComponent<Tenant> {
  constructor(
    public tenantModalRef: MDBModalRef,
    modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    service: TenantService
  ) {
    super(tenantModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl(''),
      schemaName: new FormControl('', Validators.required)
    });
  }

  onLoadValuesEdit(tenant: Tenant) {
    this.getFormGroup().patchValue({
      id: tenant.id,
      name: tenant.name,
      email: tenant.email,
      phone: tenant.phone,
      schemaName: tenant.schemaName
    });
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

  get emailForm() {
    return this.getFormGroup().get('email');
  }

  get phoneForm() {
    return this.getFormGroup().get('phone');
  }

  get schemaNameForm() {
    return this.getFormGroup().get('schemaName');
  }
}
