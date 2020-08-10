import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Tenant } from '@model/tenant';
import { Salesman } from '@model/salesman';
import { TenantService } from '@service/tenant/tenant.service';
import { SystemSalesmanService } from '@service/system-salesman/system-salesman.service';
import { DefaultEditViewComponent } from '@shared/component/default-edit-view/default-edit-view.component';
import { Page } from '@app/core/model/page';
import { ClientStatus } from '@app/core/enum/client-status';

@Component({
  selector: 'app-tenant-edit',
  templateUrl: './tenant-edit.component.html'
})
export class TenantEditComponent extends DefaultEditViewComponent<Tenant> {
  salesmans: Salesman[];
  statusList = Object.values(ClientStatus);

  constructor(
    public tenantModalRef: MDBModalRef,
    modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    service: TenantService,
    public systemSalesmanService: SystemSalesmanService
  ) {
    super(tenantModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl(''),
      schemaName: new FormControl('', Validators.required),
      salesman: new FormControl(null, Validators.required),
      status: new FormControl(null, Validators.required)
    });
  }

  onLoadValuesEdit(tenant: Tenant) {
    this.getFormGroup().patchValue({
      id: tenant.id,
      name: tenant.name,
      email: tenant.email,
      phone: tenant.phone,
      schemaName: tenant.schemaName,
      salesman: tenant.salesman.id
    });
  }

  async onLoadData() {
    this.salesmans = ((await this.systemSalesmanService.getAll().toPromise()) as Page<
    Salesman
    >).content;
  }

  public onCreateDb(){
    const tenantService =  this.service as TenantService; 
    const tenantId = this.getFormGroup().get('id').value;
    tenantService.createDb(Number(tenantId)).toPromise()
    .then(() => {
      this.toastr.success(
        this.translate.instant('Base criada com sucesso'),
        this.translate.instant('Sucesso')
      );
    }
    );
  }

  afterValidateFormSave() {
    this.salesmanForm.setValue(
      this.salesmans.find(salesman => salesman.id === +this.salesmanForm.value)
    );
    this.statusForm.setValue(
      this.statusList.find(status => status === this.statusForm.value)
    );
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

  get salesmanForm() {
    return this.getFormGroup().get('salesman');
  }

  get statusForm() {
    return this.getFormGroup().get('status');
  }
}
