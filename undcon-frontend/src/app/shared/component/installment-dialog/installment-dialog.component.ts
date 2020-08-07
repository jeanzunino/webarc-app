import { Component, ViewEncapsulation, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { Subject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

import { CloseDialogInstallmentValeus } from '@app/shared/model/close-dialog-installment-valeus';
import { Modal } from '@shared/model/modal';
import { ActionReturnDialog } from '@enum/action-return-dialog';
import { ButtonGroupValues, ButtonGroup } from '@shared/model/button-group';
import { InstallmentDialog } from '@app/shared/model/installment-dialog-model';

@Component({
  selector: 'app-installment-dialog',
  templateUrl: './installment-dialog.component.html',
  encapsulation: ViewEncapsulation.None
})
export class InstallmentDialogComponent implements OnInit, OnDestroy {
  private closeDialogInstallmentValeus = new CloseDialogInstallmentValeus();
  numberInstallments: number;
  bgInstallmentValues: ButtonGroupValues[];
  onClose = new Subject<CloseDialogInstallmentValeus>();
  numberInputs = Array(0);
  valueToInstallments = '0';
  private recalculate = false;

  constructor(public translate: TranslateService,
              public modalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService) {}

  ngOnInit() {
    const data = this.modalOptions.data as Modal;
    if (data.content) {
      const installmentDialog = data.content as InstallmentDialog;
      this.numberInstallments = installmentDialog.numberInstallments;

      const bgInstallment = new ButtonGroup();
      for (let qtd = 1; qtd <= this.numberInstallments; qtd++) {
        bgInstallment.set(`${qtd}-x`, `${qtd}x`);
      }
      this.bgInstallmentValues = bgInstallment.get();
      this.valueToInstallments = Number(installmentDialog.valueToInstallments).toFixed(2);
      this.recalculate = true;
    }
  }

  ngOnDestroy() {
    this.onClose.next(this.closeDialogInstallmentValeus);
  }

  confirm() {
    if (this.validInstallment()) {
      this.closeDialogInstallmentValeus.action = ActionReturnDialog.CONFIRM;
      this.modalRef.hide();
    }
  }

  private validInstallment() {
    const totalValue = Number(this.getTotalValueOfInstallmentsOrInstallmentZeroed());
    if (totalValue === 0) {
      this.toastr.error(
        this.translate.instant('Existem parcelas com valores não definidos'),
        this.translate.instant('Erro')
      );
    } else if (totalValue !== Number(this.valueToInstallments)) {
      this.toastr.error(
        this.translate.instant('O valor das parcelas não é igual ao valor final'),
        this.translate.instant('Erro')
      );
      return false;
    }
    return true;
  }

  cancel() {
    this.closeDialogInstallmentValeus.action = ActionReturnDialog.CANCEL;
    this.modalRef.hide();
  }

  generateInputs(numberInputs: string) {
    this.numberInstallments = Number(numberInputs.split('-')[0]);
    this.numberInputs = Array(this.numberInstallments);
    this.recalculate = true;
  }

  calculateValues() {
    for (let qtd = 0; qtd < this.numberInstallments; qtd++) {
      const element = this.getField(`installment-${qtd}`);
      if (element) {
        let finalValue = (Number(this.valueToInstallments) / this.numberInstallments).toFixed(2);
        if ((qtd + 1) === this.numberInstallments) {
          const value = (Number(this.valueToInstallments) - (Number(finalValue) * this.numberInstallments)).toFixed(2);
          finalValue = (Number(finalValue) + Number(value)).toFixed(2);
        }
        element.value = finalValue;
      }
    }
  }

  private getTotalValueOfInstallmentsOrInstallmentZeroed() {
    let total = 0;
    for (let qtd = 0; qtd < this.numberInstallments; qtd++) {
      const element = this.getField(`installment-${qtd}`);
      const value = Number(element.value);
      if (value !== 0) {
        total += Number(element.value);
      } else {
        return Number(0).toFixed(2);
      }
    }
    return total.toFixed(2);
  }

  private getField(id: string) {
    return document.getElementById(id) as HTMLInputElement;
  }

  recalculateValues(installment) {
    if (this.recalculate && installment === (this.numberInstallments - 1)) {
      this.calculateValues();
      this.recalculate = false;
    }
  }
}
