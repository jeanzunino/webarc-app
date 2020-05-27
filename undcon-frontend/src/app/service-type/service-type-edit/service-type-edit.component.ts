import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { ServiceType } from '@model/service-type';
import { ServiceTypeService } from '@service/service-type/service-type.service';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';

@Component({
  selector: 'app-service-type-edit',
  templateUrl: './service-type-edit.component.html'
})
export class ServiceTypeEditComponent extends DefaultEditViewComponent<
  ServiceType
> {
  constructor(
    public serviceTypeModalRef: MDBModalRef,
    modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    service: ServiceTypeService
  ) {
    super(serviceTypeModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      description: new FormControl(''),
      price: new FormControl('')
    });
  }

  onLoadValuesEdit(serviceType: ServiceType) {
    this.getFormGroup().patchValue({
      id: serviceType.id,
      name: serviceType.name,
      description: serviceType.description,
      price: serviceType.price
    });
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

  get descriptionForm() {
    return this.getFormGroup().get('description');
  }

  get priceForm() {
    return this.getFormGroup().get('price');
  }
}
