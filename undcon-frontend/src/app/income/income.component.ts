import { ToastrService } from 'ngx-toastr';
import { ActionReturnDialog } from '@enum/action-return-dialog';
import { CloseDialogValues } from '@shared/model/close-dialog-values';
import { takeUntil } from 'rxjs/operators';
import { Component, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';
import { Subject } from 'rxjs';

import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@core/enum/format-enum';
import { Income } from '@app/core/model/income';
import { IncomeService } from '@app/core/service/income/income.service';
import { PaymentStatus } from '@app/core/enum/payment-status';
import { openConfimDialog } from '@shared/utils/utils';
import { ConfirmDialogModel } from '@app/shared/model/confirm-dialog-model';

@Component({
  selector: 'app-income',
  templateUrl: 'income.component.html',
})
export class IncomeComponent extends GridViewComponent<Income> implements OnDestroy {
  tableValues = new Table()
    .set('description', 'income.description')
    .set('duaDate', 'income.duaDate')
    .set('paymentDate', 'income.paymentDate')
    .set('paymentType', 'income.paymentType', FormatEnum.PAYMENT_TYPE)
    .set('value', 'income.value', FormatEnum.MONEY)
    .set('paymentStatus', 'income.paymentStatus', FormatEnum.PAYMENT_STATUS)
    .set('customer.name', 'income.customer')
    .get();
  description = null;
  status: PaymentStatus = PaymentStatus.PENDING;

  statusList = Object.values(PaymentStatus);
  private ngUnsubscribeLocal = new Subject();

  constructor(
    service: IncomeService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService,
    public is: IncomeService,
    private toastr: ToastrService
  ) {
    super(service, activatedRoute, modalService);
  }

  ngOnDestroy() {
    this.ngUnsubscribeLocal.next();
    this.ngUnsubscribeLocal.complete();
  }

  onClickItem(item) {
    //Não possui tela de adionar
  }

  open() {
    //Não possui tela de adionar
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('description', QueryFilterEnum.CONTAINS_IC), this.description);
    params.set(getQueryFilter('paymentStatus', QueryFilterEnum.EQUALS), this.status);
    this.onSearchParams(params);
  }

  onClear() {
    this.description = null;
    this.status = PaymentStatus.PENDING;
    this.onClearParams();
  }

  addRemoveIncomeItem(income: Income) {
    const pago = income.paymentDate ? 'NÃO PAGO' : 'PAGO';
    openConfimDialog(new ConfirmDialogModel(`Deseja alterar a situação do recebimento ${income.description} para ${pago}?`)).content.onClose
      .pipe(takeUntil(this.ngUnsubscribeLocal))
      .subscribe((values: CloseDialogValues) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          this.addRemoveIncome(income);
        }
      });
  }

  private async addRemoveIncome(income: Income) {
    if(income.paymentStatus === PaymentStatus.CANCELED){
      this.toastr.info(
        'Recebimento cancelado não pode ser alterado!',
        'Aviso'
      );
    }
    income.paymentStatus = income.paymentStatus === PaymentStatus.PENDING ? PaymentStatus.SETTLED : PaymentStatus.PENDING;
    await this.is.updateStatus(income).toPromise()
      .then(() => {
        this.toastr.success(
          'Recebimento atualizado!',
          'Sucesso'
        );
        this.reloadItems(0);
      });
  }
}
