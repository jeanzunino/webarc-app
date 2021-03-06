import { ActionReturnDialog } from '@enum/action-return-dialog';
import { CloseDialogValues } from '@shared/model/close-dialog-values';
import { takeUntil } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { Component, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';
import { Subject } from 'rxjs';

import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@core/enum/format-enum';
import { Expense } from '@app/core/model/expense';
import { ExpenseService } from '@app/core/service/expense/expense.service';
import { PaymentStatus } from '@app/core/enum/payment-status';
import { openConfimDialog } from '@shared/utils/utils';
import { ConfirmDialogModel } from '@app/shared/model/confirm-dialog-model';

@Component({
  selector: 'app-expense',
  templateUrl: 'expense.component.html',
})
export class ExpenseComponent extends GridViewComponent<Expense> implements OnDestroy {
  tableValues = new Table()
  .set('description', 'expense.description')
  .set('duaDate', 'expense.duaDate')
  .set('paymentDate', 'expense.paymentDate')
  .set('paymentType', 'expense.paymentType', FormatEnum.PAYMENT_TYPE)
  .set('value', 'expense.value', FormatEnum.MONEY)
  .set('paymentStatus', 'expense.paymentStatus', FormatEnum.PAYMENT_STATUS)
  .set('provider.name', 'expense.provider')
  .get();
  description = null;
  status: PaymentStatus = PaymentStatus.PENDING;

  statusList = Object.values(PaymentStatus);
  private ngUnsubscribeLocal = new Subject();

  constructor(
    service: ExpenseService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService,
    public es: ExpenseService,
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

  public onSearch() {
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

  addRemoveExpenseItem(expense: Expense) {
    const pago = expense.paymentDate ? 'NÃO PAGO' : 'PAGO';
    openConfimDialog(new ConfirmDialogModel(`Deseja alterar a situação do pagamento ${expense.description} para ${pago}?`)).content.onClose
      .pipe(takeUntil(this.ngUnsubscribeLocal))
      .subscribe((values: CloseDialogValues) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          this.addRemoveExpense(expense);
        }
      });
  }

  private async addRemoveExpense(expense: Expense) {
    if(expense.paymentStatus === PaymentStatus.CANCELED){
      this.toastr.info(
        'Pagamento cancelado não pode ser alterado!',
        'Aviso'
      );
    }
    expense.paymentStatus = expense.paymentStatus === PaymentStatus.PENDING ? PaymentStatus.SETTLED : PaymentStatus.PENDING;
    await this.es.updateStatus(expense).toPromise()
      .then(() => {
        this.toastr.success(
          'Pagamento atualizado!',
          'Sucesso'
        );
        this.reloadItems(0);
      });
  }
}
