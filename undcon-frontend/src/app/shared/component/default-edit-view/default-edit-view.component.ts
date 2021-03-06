import { OnInit, OnDestroy, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';


import { EntityService } from '@service/entity/entity.service';
import { Modal } from '@shared/model/modal';
import { CloseDialogValues } from '@shared/model/close-dialog-values';

export abstract class DefaultEditViewComponent<T> implements OnInit, OnDestroy, AfterViewInit {
  private closeDialogValues = new CloseDialogValues();
  formGroup: FormGroup;
  data: Modal;
  onClose = new Subject<CloseDialogValues>();
  @ViewChild('firstInput') firstInput: ElementRef;

  constructor(
    public modalRef: MDBModalRef,
    public modalOptions: ModalOptions,
    public toastr: ToastrService,
    public translate: TranslateService,
    protected service: EntityService<T>
  ) {}

  ngAfterViewInit() {
    setTimeout(()=>{ // this will make the execut
      this.firstInput.nativeElement.focus();
    },0);
  }

  ngOnInit() {
    this.formGroup = this.createFormGroup();
    this.onLoadValues();
    this.onLoadData();
    this.loadPage();

    this.closeDialogValues.hasChange = false;
  }

  loadPage() {}

  ngOnDestroy() {
    this.onClose.next(this.closeDialogValues);
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

  validForm() {
    return true;
  }

  onLoadData() {}

  onDelete() {
    if (this.data.content) {
      this.service
        .delete(this.getFormGroup().value)
        .toPromise()
        .then((teste) => {
          this.closeDialogValues.hasChange = true;
          this.modalRef.hide();
        });
    }
  }

  afterValidateFormSave() {}

  onSave() {
    if (this.validForm()) {
      this.afterValidateFormSave();
      if (!this.data.content) {
        this.service
          .post(this.getFormGroup().value)
          .toPromise()
          .then(response => {
            this.closeDialogValues.hasChange = true;
            this.modalRef.hide();
          });
      } else {
        this.service
          .put(this.getFormGroup().value)
          .toPromise()
          .then(response => {
            this.closeDialogValues.hasChange = true;
            this.modalRef.hide();
          });
      }
    }
  }
}
