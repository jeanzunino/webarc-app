import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Pdv } from '@app/core/model/pdv';
import { PdvResume } from '@app/core/model/pdv-resume';
import { PdvService } from '@app/core/service/pdv/pdv.service';
import { DefaultEditViewComponent } from '@app/shared/component/default-edit-view/default-edit-view.component';
import { TranslateService } from '@ngx-translate/core';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-close-pdv',
  templateUrl: './close-pdv.component.html',
  styleUrls: ['./close-pdv.component.scss']
})
export class ClosePdvComponent extends DefaultEditViewComponent<Pdv> {

  constructor(public modalRef: MDBModalRef,
    modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    public service: PdvService) {
      super(modalRef, modalOptions, toastr, translate, service);
}

createFormGroup(){
return new FormGroup({
  id: new FormControl(null),
  pdvValueOpen: new FormControl(''),
  pdvValueSale: new FormControl(''),
  pdvValueClose: new FormControl('', Validators.required),
  login: new FormControl('', Validators.required),
  password: new FormControl('', Validators.required)
});
}

async onLoadValues(){
//Não Edita o registro, para fechar ocaixa é outra tela
const pdvResume = await this.service.getResumePdv().toPromise();
this.getFormGroup().patchValue({
  pdvValueOpen: pdvResume.pdv.openingValue,
  pdvValueSale: pdvResume.saleValue
});
}

onLoadValuesEdit(item: Pdv) {
}

get pdvValueCloseForm() {
  return this.getFormGroup().get('pdvValueClose');
}

get pdvValueOpenForm() {
  return this.getFormGroup().get('pdvValueOpen');
}

get pdvValueSaleForm() {
  return this.getFormGroup().get('pdvValueSale');
}


get loginForm() {
  return this.getFormGroup().get('login');
}

get passwordForm() {
  return this.getFormGroup().get('password');
}

}
