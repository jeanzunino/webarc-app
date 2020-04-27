import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Provider } from '@model/provider';
import { ProviderService } from '@service/provider/provider.service';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';
import { Modal } from '@shared/model/modal';

@Component({
  selector: 'app-provider-edit',
  templateUrl: './provider-edit.component.html',
  styleUrls: ['./provider-edit.component.scss']
})
export class ProviderEditComponent extends DefaultEditViewComponent<Provider> {

  constructor(public providerModalRef: MDBModalRef,
              modalOptions: ModalOptions,
              toastr: ToastrService,
              translate: TranslateService,
              service: ProviderService) {
    super(providerModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      phone: new FormControl('')
    });
  }

  onLoadValuesEdit(provider: Provider) {
    this.getFormGroup().patchValue({
      id: provider.id,
      name: provider.name,
      phone: provider.phone
    });
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

  get phoneForm() {
    return this.getFormGroup().get('phone');
  }

}
