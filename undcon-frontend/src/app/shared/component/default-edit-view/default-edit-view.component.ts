import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { EntityService } from '@service/entity/entity.service';
import { Page } from '@model/page';
import { SharedInjector } from '@shared/shared.module';
import { Modal } from '@shared/model/modal';

export abstract class DefaultEditViewComponent<T> implements OnInit {

  formGroup: FormGroup;
  data: Modal;

  constructor(public modalRef: MDBModalRef,
    public modalOptions: ModalOptions,
    public toastr: ToastrService,
    translate: TranslateService,
    protected service: EntityService<T>) {
  }

  ngOnInit() {
    this.formGroup = this.createFormGroup();
    this.onLoadValues();
    this.onLoadData();
  }

  abstract createFormGroup();

  getFormGroup() {
    return this.formGroup;
  }

  async onLoadValues() {
    this.data = this.modalOptions.data as Modal;
    if (this.data.content) {
      const item = this.data.content as T;
      this.onLoadValuesEdit(item);
    }
  }

  abstract onLoadValuesEdit(item);

  validForm(){
    return true;
  }

  onLoadData() {

  }

  onDelete() {
      if (this.data.content) {
        this.service.delete(this.getFormGroup().value).toPromise()
          .then(teste => {
            this.modalRef.hide();
          });
      }
  }

  afterValidateFormSave(){

  }

  onSave() {
    if (this.validForm()) {
      this.afterValidateFormSave();
      if (!this.data.content) {
        this.service.post(this.getFormGroup().value).toPromise()
          .then(teste => {
            this.modalRef.hide();
          });
      } else {
        this.service.put(this.getFormGroup().value).toPromise()
          .then(teste => {
            this.modalRef.hide();
          });
      }
    }
  }
}
