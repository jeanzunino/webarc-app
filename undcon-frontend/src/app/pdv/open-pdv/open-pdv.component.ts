import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Pdv } from '@model/pdv';
import { PdvService } from '@service/pdv/pdv.service';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';

@Component({
  selector: 'app-open-pdv',
  templateUrl: './open-pdv.component.html',
  styleUrls: ['./open-pdv.component.scss']
})
export class OpenPdvComponent extends DefaultEditViewComponent<Pdv> {

  constructor(public modalRef: MDBModalRef,
              modalOptions: ModalOptions,
              toastr: ToastrService,
              translate: TranslateService,
              service: PdvService) {
                super(modalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup(){
    return new FormGroup({
      id: new FormControl(null),
      pdvValue: new FormControl('', Validators.required),
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  onLoadValuesEdit(item: Pdv){
      //Não Edita o registro, para fechar ocaixa é outra tela
  }

  get pdvValueForm() {
    return this.getFormGroup().get('pdvValue');
  }


  get loginForm() {
    return this.getFormGroup().get('login');
  }

  get passwordForm() {
    return this.getFormGroup().get('password');
  }

}
