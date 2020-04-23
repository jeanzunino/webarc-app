import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { ProviderService } from '@service/provider/provider.service';
import { Modal } from '@shared/model/modal';

@Component({
  selector: 'app-provider-edit',
  templateUrl: './provider-edit.component.html',
  styleUrls: ['./provider-edit.component.scss']
})
export class ProviderEditComponent implements OnInit {

  providerFormGroup: FormGroup;
  data: Modal;

  constructor(public providerModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: ProviderService) {
  }

  ngOnInit() {
    this.providerFormGroup = new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      phone: new FormControl('')
    });
    this.onLoadValues();

  }

  async onLoadValues() {
    this.data = this.modalOptions.data as Modal;
    if (this.data.content) {
      const provider = this.data.content;
      this.providerFormGroup.patchValue({
        id: provider.id,
        name: provider.name,
        phone: provider.phone,
      });
    }
  }

  get nameForm() {
    return this.providerFormGroup.get('name');
  }

  get phoneForm() {
    return this.providerFormGroup.get('phone');
  }

  private validForm() {
    return true;
  }


  onSave() {
    if (this.validForm()) {
      if (this.data.content === undefined) {
        this.service.post(this.providerFormGroup.value).toPromise()
        .then(teste => {
          console.log(teste)
        });
      } else {
        this.service.put(this.providerFormGroup.value, parseInt(this.providerFormGroup.get('id').value)).toPromise()
        .then(teste => {
          console.log(teste)
        });
      }
    }
  }
}
