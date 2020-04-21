import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Provider } from '@model/provider';
import { ProviderService } from '@service/provider/provider.service';

export class Teste {
  provider: Provider
}

@Component({
  selector: 'app-provider-edit',
  templateUrl: './provider-edit.component.html',
  styleUrls: ['./provider-edit.component.scss']
})
export class ProviderEditComponent implements OnInit {

  providerFormGroup: FormGroup;

  constructor(public providerModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: ProviderService) {
  }

  ngOnInit() {
    this.providerFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      phone: new FormControl('')
    });
    let dados = this.modalOptions.data as Teste
    if (dados.provider) {
      this.nameForm.setValue(dados.provider.name);
      this.phoneForm.setValue(dados.provider.phone);
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
      alert(this.nameForm.value)
    }
  }
}
