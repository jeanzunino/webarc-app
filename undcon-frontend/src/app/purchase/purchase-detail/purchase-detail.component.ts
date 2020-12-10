import { ExpenseService } from './../../core/service/expense/expense.service';
import { Expense } from '@app/core/model/expense';
import { PurchaseExpenseResponse } from '../../core/service/dtos/purchase-expense-response';
import { PurchaseExpense } from '../../core/model/purchase-expense';
import { PurchaseExpenseDto } from '../../core/service/dtos/purchase-expense-dto';
import { PurchaseTotal } from './../../core/model/purchase-total';
import { PurchaseItem } from './../../core/model/purchase-item';
import { PurchaseService } from './../../core/service/purchase/purchase.service';
import { Provider } from '@model/provider';
import { Purchase } from '@model/purchase';
import { ProviderService } from '@service/provider/provider.service';
import { OnInit } from '@angular/core';
import { InstallmentDialogComponent } from '@component/installment-dialog/installment-dialog.component';
import { openSimpleDialog } from '@shared/utils/utils';
import { PaymentType } from '../../core/enum/payment-type';
import { ButtonGroup, ButtonGroupValues } from '../../shared/model/button-group';
import { NgxSpinnerService } from 'ngx-spinner';
import { Component, ViewEncapsulation, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { Page } from '@model/page';
import { SharedInjector } from '@shared/shared.module';
import { getQueryFilter } from '@shared/utils/utils';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { BillingStatus } from '@app/core/enum/billing-status';
import { Product } from '@model/product';
import { ProductService } from '@service/product/product.service';
import { ServiceType } from '@model/service-type';
import { ServiceTypeService } from '@service/service-type/service-type.service';
import { Table } from '@shared/model/table';
import { FormatEnum } from '@enum/format-enum';
import { Item } from '@core/model/item';
import { openConfimDialog } from '@shared/utils/utils';
import { getEnumTranslation } from '@shared/utils/utils';
import { ConfirmDialogModel } from '@app/shared/model/confirm-dialog-model';
import { CloseDialogValues } from '@app/shared/model/close-dialog-values';
import { ActionReturnDialog } from '@enum/action-return-dialog';
import { ItemType } from '@app/core/enum/item-type';
import { InstallmentDialog } from '@app/shared/model/installment-dialog-model';
import { CloseDialogInstallmentValeus } from '@app/shared/model/close-dialog-installment-valeus';

@Component({
  selector: 'app-purchase-detail',
  templateUrl: './purchase-detail.component.html',
  styleUrls: ['./purchase-detail.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class PurchaseDetailComponent implements OnInit, OnDestroy {

  private ngUnsubscribe = new Subject();
  spinner = SharedInjector.get(NgxSpinnerService);
  showPanelHeader = false;

  purchaseItems: Page<PurchaseItem>;
  purchaseTotal = new PurchaseTotal();
  currentPage = 0;
  tableValues = new Table()
    .set('name', 'purchase-item.name')
    .set('price', 'purchase-item.price', FormatEnum.MONEY)
    .set('quantity', 'purchase-item.quantity')
    .set('itemType', 'purchase-item.itemType', FormatEnum.ITEM_TYPE)
    .set('subTotalItem', 'purchase-item.subTotalItem', FormatEnum.MONEY)
    .get();

  expenseItems: Page<Expense>;
  expenseCurrentPage = 0;
  expenseTableValues = new Table()
    .set('duaDate', 'expense.duaDate', FormatEnum.DATE_PIPE)
    .set('paymentType', 'expense.paymentType', FormatEnum.PAYMENT_TYPE)
    .set('value', 'expense.value', FormatEnum.MONEY)
    .get();

  entity: Purchase;
  colorPanelHeader = '';
  iconPanelHeader = '';
  bgPaymentTypeValues: ButtonGroupValues[];
  purchaseExpenseDto = new PurchaseExpenseDto();
  reloadValuesPreviousScreen = false;
  hasParcels = false;

  @ViewChild('ngAutoCompleteProvider') ngAutoCompleteProvider;
  providers: Provider[];
  providerKeyword = 'name';
  providerInitialValue;
  isLoadingProvider = false;

  @ViewChild('ngAutoCompleteProduct') ngAutoCompleteProduct;
  private productSelect: Product;
  products: Product[];
  productKeyword = 'name';
  productPrice = '0';
  productQtd = 0;
  productPriceSubtotal = '0';
  isLoadingProduct = false;

  @ViewChild('ngAutoCompleteService') ngAutoCompleteService;
  private serviceSelect: ServiceType;
  services: ServiceType[];
  serviceKeyword = 'name';
  servicePrice = '0';
  serviceQtd = 0;
  servicePriceSubtotal = '0';
  isLoadingService = false;

  purchasesExpenses: PurchaseExpense[] = [];
  insertedPayments;

  constructor(private router: Router,
              private rt: ActivatedRoute,
              private providerService: ProviderService,
              private ps: ProductService,
              private sts: ServiceTypeService,
              private datePipe: DatePipe,
              private toastr: ToastrService,
              private translate: TranslateService,
              private expenseService: ExpenseService,
              private purchaseService: PurchaseService) { }

  ngOnInit() {
    this.loadPage();
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  private async loadPage() {
    this.entity = new Purchase();
    if (!this.isNew()) {
      this.entity = this.rt.snapshot.data.entity as Purchase;
      this.providerInitialValue = this.entity.provider;
      this.setPanelHeaderStatus();
      this.showPanelHeader = true;
      this.purchaseItems = this.rt.snapshot.data.purchaseItens;
      this.expenseItems = this.rt.snapshot.data.expenseItens;
      this.setPurchaseTotal();
      this.setBgPaymentType();
    }
  }

  setBgPaymentType() {
    const bgPaymentType = new ButtonGroup();
    Object.keys(PaymentType).filter(type => isNaN(type as any) && type !== 'values')
      .forEach(paymentType => bgPaymentType.set(paymentType, this.translate.instant(`enums.payment-type.${paymentType}`)));
    this.bgPaymentTypeValues = bgPaymentType.get();
  }

  private async setPurchaseTotal() {
    this.purchaseTotal = await this.purchaseService.getPurchaseTotal(this.entity.id).toPromise();
  }

  private setPanelHeaderStatus() {
    switch (this.entity.status) {
      case BillingStatus.CREATED: {
        this.colorPanelHeader = 'background-color-deepskyblue';
        this.iconPanelHeader = 'fa fa-cart-plus';
        break;
      }
      case BillingStatus.TO_BILL: {
        this.colorPanelHeader = 'background-color-orange';
        this.iconPanelHeader = 'fa fa-exclamation-triangle';
        break;
      }
      case BillingStatus.BILLED: {
        this.colorPanelHeader = 'background-color-hot-pink';
        this.iconPanelHeader = 'fa fa-check';
        break;
      }
      case BillingStatus.TOTAL_BILLED: {
        this.colorPanelHeader = 'background-color-springgreen';
        this.iconPanelHeader = 'fa fa-check-double';
        break;
      }
      case BillingStatus.CANCELED: {
        this.colorPanelHeader = 'background-color-crimson';
        this.iconPanelHeader = 'fa fa-times';
        break;
      }
    }
  }

  goBack() {
    this.router.navigate(['../', {reload: this.reloadValuesPreviousScreen}], { relativeTo: this.rt.parent });
  }

  providerSelectEvent(provider: Provider) {
    this.entity.provider = provider;
  }

  productSelectEvent(product: Product) {
    this.productSelect = product;
    this.productPrice = product.purchasePrice.toFixed(2);
    this.updateProductTotal();
  }

  updateProductTotal() {
    this.productPriceSubtotal = (Number(this.productPrice) * this.productQtd).toFixed(2);
  }

  serviceSelectEvent(serviceType: ServiceType) {
    this.serviceSelect = serviceType;
    this.servicePrice = serviceType.price.toFixed(2);
    this.updateServiceTotal();
  }

  updateServiceTotal() {
    this.servicePriceSubtotal = (Number(this.servicePrice) * this.serviceQtd).toFixed(2);
  }

  async providerChangeEvent(name) {
    this.isLoadingProvider = true;
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.providers = ((await this.providerService.getAll(params).toPromise()) as Page<
      Provider
    >).content;
    this.isLoadingProvider = false;
  }

  async productChangeEvent(name) {
    this.isLoadingProduct = true;
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.products = ((await this.ps.getAll(params).toPromise()) as Page<
      Product
    >).content;
    this.isLoadingProduct = false;
  }

  async serviceChangeEvent(name) {
    this.isLoadingService = true;
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.services = ((await this.sts.getAll(params).toPromise()) as Page<
      ServiceType
    >).content;
    this.isLoadingService = false;
  }

  providerInputCleared() {
    this.providers = [];
    this.ngAutoCompleteProvider.close();
  }

  productInputCleared() {
    this.products = [];
    this.productSelect = null;
    this.productPrice = '0';
    this.productQtd = 0;
    this.productPriceSubtotal = '0';
    this.ngAutoCompleteProduct.clear();
    this.ngAutoCompleteProduct.close();
  }

  serviceInputCleared() {
    this.services = [];
    this.serviceSelect = null;
    this.servicePrice = '0';
    this.serviceQtd = 0;
    this.servicePriceSubtotal = '0';
    this.ngAutoCompleteService.clear();
    this.ngAutoCompleteService.close();
  }

  public isNew() {
    return this.rt.snapshot.params.id === 'new';
  }

  public getTitle() {
    if (!this.isNew()) {
      const status = this.translate.instant(`enums.billing-status.${this.entity.status.toUpperCase()}`);
      return `${status} - ${this.datePipe.transform(this.entity.purchaseDate, 'dd/MM/yyyy')}`.toUpperCase();
    }
    return '';
  }

  async reloadItems(page) {
    this.spinner.show();
    this.currentPage = page + 1;
    this.purchaseItems = (await this.purchaseService.getPurchaseItens(this.entity.id, page).toPromise());
    this.spinner.hide();
  }

  async reloadExpenseItems(page) {
    this.spinner.show();
    this.expenseCurrentPage = page + 1;
    const params = new Map<string, string>();
    params.set('purchase.id=', this.entity.id.toString());
    this.expenseItems = ((await this.expenseService.getAll(params, page).toPromise()) as Page<
      Expense
    >);
    this.spinner.hide();
  }

  onConfirmSave() {
    if (this.validEntity()) {
      const msg = `
        Confirma a inclusão da nova venda com os seguinte dados?<br>
        Fornecedor: ${this.entity.provider.name}<br>
        Após a inclusão os dados não poderão ser alterados!
      `;
      openConfimDialog(new ConfirmDialogModel(msg)).content.onClose
        .pipe(takeUntil(this.ngUnsubscribe))
        .subscribe((values: CloseDialogValues) => {
          if (values.action === ActionReturnDialog.CONFIRM) {
            this.onSave();
          }
        });
    }
  }

  private async onSave() {
    this.spinner.show();
    await this.purchaseService.post(this.entity)
      .toPromise()
      .then(purchase => {
        this.entity = purchase;
        this.setPanelHeaderStatus();
        this.reloadValuesPreviousScreen = true;
        if (this.isNew()) {
          this.showPanelHeader = true;
          this.router.navigate(['../', this.entity.id], { relativeTo: this.rt.parent });
        }
        this.toastr.success(
          this.translate.instant('Venda incluida com sucesso'),
          this.translate.instant('Sucesso')
        );
      });
    this.spinner.hide();
  }

  onConfirmFinalize() {
    if (this.validFinalize()) {
      const msg = `
        Confirma a finalização da venda?<br><br>
        Após a finalização só poderá Faturar ou Finalizar!
      `;
      openConfimDialog(new ConfirmDialogModel(msg)).content.onClose
        .pipe(takeUntil(this.ngUnsubscribe))
        .subscribe((values: CloseDialogValues) => {
          if (values.action === ActionReturnDialog.CONFIRM) {
            this.purchaseFinalize();
          }
        });
    }
  }

  private async purchaseFinalize() {
    await this.purchaseService.purchaseFinalize(this.entity.id).toPromise()
      .then(() => {
        this.entity.status = BillingStatus.TO_BILL;
        this.toastr.success(
          this.translate.instant('Venda finalizada com sucesso'),
          this.translate.instant('Sucesso')
        );
        this.setPanelHeaderStatus();
        this.showPanelHeader = true;
        this.setBgPaymentType();
      });
  }

  private validFinalize() {
    if (!this.purchaseItems || (this.purchaseItems && this.purchaseItems.content.length === 0)) {
      this.toastr.error(
        this.translate.instant('Não possuí itens para finalizar.'),
        this.translate.instant('Erro')
      );
      return false;
    }

    return true;
  }

  private validEntity() {
    if (!this.entity.provider) {
      this.toastr.error(
        this.translate.instant('Fornecedor não informado'),
        this.translate.instant('Erro')
      );
      return false;
    }

    return true;
  }

  async addProduct() {
    this.spinner.show();
    if (this.validProduct()) {
      const item = new Item();
      item.itemId = this.productSelect.id;
      item.quantity = this.productQtd;
      await this.purchaseService.addProductItem(this.entity.id, item).toPromise()
        .then(() => {
          this.reloadItems(0);
          this.productInputCleared();
          this.setPurchaseTotal();
          this.toastr.success(
            this.translate.instant('Produto adicionado com sucesso'),
            this.translate.instant('Sucesso')
          );
          this.spinner.hide();
        });
    }
  }

  async addService() {
    this.spinner.show();
    if (this.validService()) {
      const item = new Item();
      item.itemId = this.serviceSelect.id;
      item.quantity = this.serviceQtd;
      await this.purchaseService.addServiceItem(this.entity.id, item).toPromise()
        .then(() => {
          this.reloadItems(0);
          this.serviceInputCleared();
          this.setPurchaseTotal();
          this.toastr.success(
            this.translate.instant('Serviço adicionado com sucesso'),
            this.translate.instant('Sucesso')
          );
          this.spinner.hide();
        });
    }
  }

  confirmDeletePurchaseItem(purchaseItem: PurchaseItem) {
    const value = getEnumTranslation(purchaseItem.itemType);
    openConfimDialog(new ConfirmDialogModel(`Confirma a remoção do ${value} ${purchaseItem.name}`)).content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogValues) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          if (purchaseItem.itemType === ItemType.PRODUCT) {
            this.deleteProductItem(purchaseItem);
          } else {
            this.deleteServiceItem(purchaseItem);
          }
        }
      });
  }

  confirmDeleteExpenseItem(expense: Expense) {
     openConfimDialog(new ConfirmDialogModel(`Confirma a remoção do pagamento`)).content.onClose
       .pipe(takeUntil(this.ngUnsubscribe))
       .subscribe((values: CloseDialogValues) => {
         if (values.action === ActionReturnDialog.CONFIRM) {
          this.deleteExpense(expense);
         }
       });
  }

  private deleteExpense(expense: Expense) {
    this.spinner.show();
    this.expenseService.delete(expense).toPromise()
    .then(() => {
      this.reloadExpenseItems(0);
      this.purchaseService.get(this.entity.id).toPromise()
      .then((purchase: Purchase) => {
        this.entity.status = purchase.status;
        this.getTitle();
        this.setPanelHeaderStatus();
      });
      this.setPurchaseTotal();
      this.toastr.success(
        this.translate.instant('Pagamento removido com sucesso'),
        this.translate.instant('Sucesso')
      );
    });
    this.spinner.hide();
  }

  private deleteProductItem(purchaseItem: PurchaseItem) {
    this.spinner.show();
    this.purchaseService.deleteProductItem(this.entity.id, purchaseItem.id).toPromise()
    .then(() => {
      this.reloadItems(0);
      this.setPurchaseTotal();
      this.toastr.success(
        this.translate.instant('Produto removido com sucesso'),
        this.translate.instant('Sucesso')
      );
    });
    this.spinner.hide();
  }

  private deleteServiceItem(purchaseItem: PurchaseItem) {
    this.spinner.show();
    this.purchaseService.deleteServiceItem(this.entity.id, purchaseItem.id).toPromise()
    .then(() => {
      this.reloadItems(0);
      this.setPurchaseTotal();
      this.toastr.success(
        this.translate.instant('Serviço removido com sucesso'),
        this.translate.instant('Sucesso')
      );
    });
    this.spinner.hide();
  }

  private validProduct() {
    if (!this.productSelect) {
      this.toastr.error(
        this.translate.instant('Nenhum produto selecionado'),
        this.translate.instant('Erro')
      );
      return false;
    }

    if (!this.productQtd) {
      this.toastr.error(
        this.translate.instant('A quantidade do produto deve ser informada'),
        this.translate.instant('Erro')
      );
      return false;
    }

    return true;
  }

  private validService() {
    if (!this.serviceSelect) {
      this.toastr.error(
        this.translate.instant('Nenhum serviço selecionado'),
        this.translate.instant('Erro')
      );
      return false;
    }

    if (!this.serviceQtd) {
      this.toastr.error(
        this.translate.instant('A quantidade do serviço deve ser informada'),
        this.translate.instant('Erro')
      );
      return false;
    }

    return true;
  }

  onConfirmCancel() {
    const msg = `Confirma o cancelamento da venda?`;
    openConfimDialog(new ConfirmDialogModel(msg)).content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogValues) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          this.purchaseCancel();
        }
      });
  }

  private async purchaseCancel() {
    await this.purchaseService.purchaseCancel(this.entity.id).toPromise()
      .then(() => {
        this.entity.status = BillingStatus.CANCELED;
        this.toastr.success(
          this.translate.instant('Venda cancelada com sucesso'),
          this.translate.instant('Sucesso')
        );
      });
  }

  bgPaymentTypeSelect(paymentTypeSelect: PaymentType) {
    this.clearPaymentType();
    this.purchaseExpenseDto = new PurchaseExpenseDto();
    this.purchaseExpenseDto.paymentType = paymentTypeSelect;
  }

  private clearPaymentType() {
    this.removeParcels();
  }

  installment() {
    openSimpleDialog(new InstallmentDialog(this.purchaseExpenseDto.value), InstallmentDialogComponent)
      .content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogInstallmentValeus) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          this.hasParcels = true;
          this.purchasesExpenses = values.installmentDtos;
        }
      });
  }

  removeParcels() {
    this.purchasesExpenses = [];
    this.hasParcels = false;
  }

  launchPayment() {
    if (this.hasParcels) {
      this.purchaseService.launchPaymentPurchasesExpenses(this.entity.id, this.purchasesExpenses)
        .toPromise()
        .then((response: PurchaseExpenseResponse) => {
          this.purchaseTotal.amountPaid = response.amountPaid;
          this.purchaseTotal.amountPayable = response.amountPayable;
          this.entity.status = response.purchase.status;
          this.launchPaymentSucess();
        });
    } else {
      if (this.validLaunchPayment()) {
        this.purchaseService.launchPaymentPurchaseExpense(this.entity.id, this.purchaseExpenseDto)
          .toPromise()
          .then((response: PurchaseExpenseResponse) => {
            this.purchaseTotal.amountPaid = response.amountPaid;
            this.purchaseTotal.amountPayable = response.amountPayable;
            this.entity.status = response.purchase.status;
            this.launchPaymentSucess();
          });
      }
    }
  }

  private launchPaymentSucess() {
    this.reloadExpenseItems(0);
    this.setPanelHeaderStatus();
  }

  private validLaunchPayment() {
    if (!this.purchaseExpenseDto.value) {
      this.toastr.error(
        this.translate.instant('Nenhum valor informado'),
        this.translate.instant('Erro')
      );
      return false;
    }

    if (this.isCash || this.isBankCheck || this.isBankSlip) {
      if (!this.purchaseExpenseDto.duaDate) {
        this.toastr.error(
          this.translate.instant('Nenhuma data de vencimento informada'),
          this.translate.instant('Erro')
        );
        return false;
      }
    }

    if (this.isBankCheck()) {
      if (!this.purchaseExpenseDto.check.number) {
        this.toastr.error(
          this.translate.instant('Número do cheque não informado'),
          this.translate.instant('Erro')
        );
        return false;
      }

      if (!this.purchaseExpenseDto.check.emitter) {
        this.toastr.error(
          this.translate.instant('Emissor do cheque não informado'),
          this.translate.instant('Erro')
        );
        return false;
      }

      if (!this.purchaseExpenseDto.check.personDocument) {
        this.toastr.error(
          this.translate.instant('Documento do emissor do cheque não informado'),
          this.translate.instant('Erro')
        );
        return false;
      }
    }

    return true;
  }

  updateDate(event) {
    if (!event.srcElement.value) {
      this.purchaseExpenseDto.duaDate = null;
    } else {
      const date = new Date();
      const separateDate = event.srcElement.value.split('-');
      date.setUTCDate(separateDate[2]);
      date.setUTCMonth(separateDate[1] - 1);
      date.setUTCFullYear(separateDate[0]);
      this.purchaseExpenseDto.duaDate = date;
    }
  }

  isCash() {
    return this.purchaseExpenseDto.paymentType === PaymentType.CASH;
  }

  isBankCheck() {
    return this.purchaseExpenseDto.paymentType === PaymentType.BANK_CHECK;
  }

  isCreditCard() {
    return this.purchaseExpenseDto.paymentType === PaymentType.CREDIT_CARD;
  }

  isBankSlip() {
    return this.purchaseExpenseDto.paymentType === PaymentType.BANK_SLIP;
  }

  isDebitCard() {
    return this.purchaseExpenseDto.paymentType === PaymentType.DEBIT_CARD;
  }

  isToBill() {
    return this.isNew() ? false : this.entity.status === BillingStatus.TO_BILL;
  }

  isBilled() {
    return this.isNew() ? false : this.entity.status === BillingStatus.BILLED;
  }

  isTotalBilled() {
    return this.isNew() ? false : this.entity.status === BillingStatus.TOTAL_BILLED;
  }

  isCanceled() {
    return this.isNew() ? false : this.entity.status === BillingStatus.CANCELED;
  }

  isCreated() {
    return this.isNew() ? false : this.entity.status === BillingStatus.CREATED;
  }
}
