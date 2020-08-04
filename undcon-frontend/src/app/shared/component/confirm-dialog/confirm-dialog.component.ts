import { Component, OnInit, OnDestroy } from '@angular/core';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { TranslateService } from '@ngx-translate/core';
import { Modal } from '@app/shared/model/modal';
import { ConfirmDialogModel } from '@shared/model/confirm-dialog-model';
import { Subject } from 'rxjs';
import { CloseDialogValues } from '@app/shared/model/close-dialog-values';
import { ActionReturnDialog } from '@app/core/enum/action-return-dialog';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html'
})
export class ConfirmDialogComponent implements OnInit, OnDestroy {
  private closeDialogValues = new CloseDialogValues();
  data: Modal;
  item: ConfirmDialogModel;
  onClose = new Subject<CloseDialogValues>();

  constructor(public translate: TranslateService,
              public modalRef: MDBModalRef,
              public modalOptions: ModalOptions) {}

  ngOnInit() {
    this.data = this.modalOptions.data as Modal;
    if (this.data.content) {
      this.item = this.data.content as ConfirmDialogModel;
    }
  }

  ngOnDestroy() {
    this.onClose.next(this.closeDialogValues);
  }

  confirm() {
    this.closeDialogValues.action = ActionReturnDialog.CONFIRM;
    this.modalRef.hide();
  }

  cancel() {
    this.closeDialogValues.action = ActionReturnDialog.CANCEL;
    this.modalRef.hide();
  }
}
