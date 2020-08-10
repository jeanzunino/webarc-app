import { PaymentType } from './../../../core/enum/payment-type';
import { SaleIncome } from '@model/sale-income';
import { LocalizedDatePipe } from '@core/pipes/localized-date-pipe';
import { Component, ViewEncapsulation, OnDestroy, OnInit } from '@angular/core';
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
  firstInstallmentDate = '';
  installments: number[] = [];
  salesIncomes: SaleIncome[] = [];

  constructor(public translate: TranslateService,
              public modalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private localizedDatePipe: LocalizedDatePipe) {}

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
      const currentMonth = (new Date()).getMonth();
      this.firstInstallmentDate = this.localizedDatePipe.transform((new Date()).setMonth(currentMonth + 1), 'yyyy-MM-ddThh:mm');
    }
  }

  ngOnDestroy() {
    this.onClose.next(this.closeDialogInstallmentValeus);
  }

  confirm() {
    if (this.validInstallment()) {
      this.createInstallment();
      this.closeDialogInstallmentValeus.action = ActionReturnDialog.CONFIRM;
      this.closeDialogInstallmentValeus.firstInstallmentDate = this.firstInstallmentDate;
      this.closeDialogInstallmentValeus.salesIncomes = this.salesIncomes;
      this.modalRef.hide();
    }
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
      const nextMonth = (new Date(dateInstallment)).getMonth() + 1;
      const saleIncome = new SaleIncome();
      saleIncome.paymentType = PaymentType.CASH;
      saleIncome.value = value;
      saleIncome.duaDate = dateInstallment;
      this.salesIncomes.push(saleIncome);
      dateInstallment = this.localizedDatePipe.transform((new Date(dateInstallment)).setMonth(nextMonth), 'yyyy-MM-ddThh:mm');
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
    const firstInstallmentDate = (new Date(this.firstInstallmentDate)).getMonth();
    return this.localizedDatePipe.transform((new Date(this.firstInstallmentDate))
      .setMonth(firstInstallmentDate + parcelNumber), 'dd/MM/yyyy hh:mm');
  }
}
