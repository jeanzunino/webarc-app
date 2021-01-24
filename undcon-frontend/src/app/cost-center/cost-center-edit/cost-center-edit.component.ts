import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { CostCenter } from '@model/cost-center';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';
import { CostCenterService } from '@app/core/service/cost-center/cost-center.service';

@Component({
  selector: 'app-cost-center-edit',
  templateUrl: './cost-center-edit.component.html'
})
export class CostCenterEditComponent extends DefaultEditViewComponent<CostCenter> {
  constructor(
    public modalRef: MDBModalRef,
    modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    service: CostCenterService
  ) {
    super(modalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required)
    });
  }

  onLoadValuesEdit(item: CostCenter) {
    this.getFormGroup().patchValue({
      id: item.id,
      name: item.name
    });
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

}
