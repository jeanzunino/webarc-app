import { SaleIncome } from '@model/sale-income';
import { Component, ViewEncapsulation, OnDestroy, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { Subject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

import { CloseDialogInstallmentValeus } from '@app/shared/model/close-dialog-installment-valeus';
import { Modal } from '@shared/model/modal';
import { ActionReturnDialog } from '@enum/action-return-dialog';
import { ButtonGroupValues, ButtonGroup } from '@shared/model/button-group';
import { InstallmentDialog } from '@shared/model/installment-dialog-model';
import { PaymentType } from '@core/enum/payment-type';

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
  firstInstallmentDate: Date;
  installments: number[] = [];
  salesIncomes: SaleIncome[] = [];
  selectDefaultItem = '6-x';

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
      const nextMonth = new Date();
      nextMonth.setMonth((new Date()).getMonth() + 1);
      this.firstInstallmentDate = nextMonth;
    }
  }

  ngOnDestroy() {
    this.onClose.next(this.closeDialogInstallmentValeus);
  }

  confirm() {
    if (this.validInstallment()) {
      this.createInstallment();
      this.closeDialogInstallmentValeus.action = ActionReturnDialog.CONFIRM;
      this.closeDialogInstallmentValeus.salesIncomes = this.salesIncomes;
      this.modalRef.hide();
    }
  }

  cancel() {
    this.closeDialogInstallmentValeus.action = ActionReturnDialog.CANCEL;
    this.modalRef.hide();
  }

  generateInputs(numberInputs: string) {
    if (this.firstInstallmentDate) {
      this.numberInstallments = Number(numberInputs.split('-')[0]);
      this.numberInputs = Array(this.numberInstallments);
      this.recalculate = true;
    }
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

  recalculateValues(installment) {
    if (this.recalculate && installment === (this.numberInstallments - 1)) {
      this.calculateValues();
      this.recalculate = false;
    }
  }

  private validInstallment() {
    const totalValue = Number(this.getTotalValueOfInstallmentsOrInstallmentZeroed());
    if (totalValue === 0) {
      this.toastr.error(
        this.translate.instant('Existem parcelas com valores não definidos'),
        this.translate.instant('Erro')
      );
      return false;
    }

    if (totalValue !== Number(this.valueToInstallments)) {
      this.toastr.error(
        this.translate.instant('O valor das parcelas não é igual ao valor final'),
        this.translate.instant('Erro')
      );
      return false;
    }

    if (!this.firstInstallmentDate) {
      this.toastr.error(
        this.translate.instant('Não possuí a data da 1ª parcela informada'),
        this.translate.instant('Erro')
      );
      return false;
    }
    return true;
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

  private createInstallment() {
    this.setInstallments();
    let dateInstallment = this.firstInstallmentDate;
    this.installments.forEach(value => {
      const nextMonth = dateInstallment.getMonth() + 1;
      const saleIncome = new SaleIncome();
      saleIncome.paymentType = PaymentType.CASH;
      saleIncome.value = value;
      saleIncome.duaDate = dateInstallment;
      this.salesIncomes.push(saleIncome);
      const nextDate = new Date(dateInstallment);
      nextDate.setMonth(nextMonth);
      dateInstallment = nextDate;
    });
  }

  private setInstallments() {
    for (let qtd = 0; qtd < this.numberInstallments; qtd++) {
      let finalValue = (Number(this.valueToInstallments) / this.numberInstallments).toFixed(2);
      if ((qtd + 1) === this.numberInstallments) {
        const value = (Number(this.valueToInstallments) - (Number(finalValue) * this.numberInstallments)).toFixed(2);
        finalValue = (Number(finalValue) + Number(value)).toFixed(2);
      }
      this.installments.push(Number(finalValue));
    }
  }

  getInstallmentDateLabel(parcelNumber: number) {
    const nextDate = new Date(this.firstInstallmentDate);
    nextDate.setMonth(nextDate.getMonth() + parcelNumber);
    return nextDate;
  }

  updateDate(event) {
    if (!event.srcElement.value) {
      this.firstInstallmentDate = null;
      this.numberInputs = Array(0);
    } else {
      const date = new Date();
      const separateDate = event.srcElement.value.split('-');
      date.setUTCDate(separateDate[2]);
      date.setUTCMonth(separateDate[1] - 1);
      date.setUTCFullYear(separateDate[0]);
      this.firstInstallmentDate = date;
      this.generateInputs(`${this.numberInstallments}-x`);
      this.calculateValues();
    }
  }
}
