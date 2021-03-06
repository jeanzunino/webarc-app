import { IncomeService } from '@app/core/service/income/income.service';
import { Income } from '@app/core/model/income';
import { SaleIncomeDto } from './../../core/service/dtos/sale-income-dto';
import { SaleTotal } from '@model/sale-total';
import { SaleIncomeResponse } from './../../core/service/dtos/sale-income-response';
import { OnInit } from '@angular/core';
import { SaleIncome } from '@model/sale-income';
import { InstallmentDialogComponent } from '@component/installment-dialog/installment-dialog.component';
import { openSimpleDialog } from '@shared/utils/utils';
import { PaymentType } from './../../core/enum/payment-type';
import { ButtonGroup, ButtonGroupValues } from './../../shared/model/button-group';
import { NgxSpinnerService } from 'ngx-spinner';
import { Component, ViewEncapsulation, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { Customer } from '@model/customer';
import { Sale } from '@model/sale';
import { Page } from '@model/page';
import { SharedInjector } from '@shared/shared.module';
import { Employee } from '@model/employee';
import { CustomerService } from '@service/customer/customer.service';
import { getQueryFilter } from '@shared/utils/utils';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { BillingStatus } from '@app/core/enum/billing-status';
import { EmployeeService } from '@service/employee/employee.service';
import { Product } from '@model/product';
import { ProductService } from '@service/product/product.service';
import { ServiceType } from '@model/service-type';
import { ServiceTypeService } from '@service/service-type/service-type.service';
import { SaleItem } from '@model/sale-item';
import { Table } from '@shared/model/table';
import { SaleService } from '@service/sale/sale.service';
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
  selector: 'app-sale-detail',
  templateUrl: './sale-detail.component.html',
  styleUrls: ['./sale-detail.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class SaleDetailComponent implements OnInit, OnDestroy {

  private ngUnsubscribe = new Subject();
  spinner = SharedInjector.get(NgxSpinnerService);
  showPanelHeader = false;

  saleItems: Page<SaleItem>;
  saleTotal = new SaleTotal();
  currentPage = 0;
  tableValues = new Table()
    .set('name', 'sale-item.name')
    .set('price', 'sale-item.price', FormatEnum.MONEY)
    .set('quantity', 'sale-item.quantity')
    .set('itemType', 'sale-item.itemType', FormatEnum.ITEM_TYPE)
    .set('subTotalItem', 'sale-item.subTotalItem', FormatEnum.MONEY)
    .get();

  incomeItems: Page<Income>;
  incomeCurrentPage = 0;
  incomeTableValues = new Table()
    .set('duaDate', 'income.duaDate', FormatEnum.DATE_PIPE)
    .set('paymentType', 'income.paymentType', FormatEnum.PAYMENT_TYPE)
    .set('value', 'income.value', FormatEnum.MONEY)
    .get();

  entity: Sale;
  colorPanelHeader = '';
  iconPanelHeader = '';
  bgPaymentTypeValues: ButtonGroupValues[];
  saleIncomeDto = new SaleIncomeDto();
  reloadValuesPreviousScreen = false;
  hasParcels = false;

  @ViewChild('ngAutoCompleteCustomer') ngAutoCompleteCustomer;
  customers: Customer[];
  customerKeyword = 'name';
  customerInitialValue;
  isLoadingCustomer = false;

  @ViewChild('ngAutoCompleteEmployee') ngAutoCompleteEmployee;
  employees: Employee[];
  employeeKeyword = 'name';
  employeeInitialValue;
  isLoadingEmployee = false;

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

  salesIncomes: SaleIncome[] = [];
  insertedPayments;

  constructor(private router: Router,
              private rt: ActivatedRoute,
              private cs: CustomerService,
              private es: EmployeeService,
              private ps: ProductService,
              private sts: ServiceTypeService,
              private ss: SaleService,
              private datePipe: DatePipe,
              private toastr: ToastrService,
              private translate: TranslateService,
              private is: IncomeService) { }

  ngOnInit() {
    this.loadPage();
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  private async loadPage() {
    this.entity = new Sale();
    if (!this.isNew()) {
      this.entity = this.rt.snapshot.data.entity as Sale;
      this.customerInitialValue = this.entity.customer;
      this.employeeInitialValue = this.entity.salesman;
      this.setPanelHeaderStatus();
      this.showPanelHeader = true;
      this.saleItems = this.rt.snapshot.data.saleItens;
      this.incomeItems = this.rt.snapshot.data.incomeItens;
      this.setSaleTotal();
      this.setBgPaymentType();
    }
  }

  setBgPaymentType() {
    const bgPaymentType = new ButtonGroup();
    Object.keys(PaymentType).filter(type => isNaN(type as any) && type !== 'values')
      .forEach(paymentType => bgPaymentType.set(paymentType, this.translate.instant(`enums.payment-type.${paymentType}`)));
    this.bgPaymentTypeValues = bgPaymentType.get();
  }

  private async setSaleTotal() {
    this.saleTotal = await this.ss.getSaleTotal(this.entity.id).toPromise();
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

  customerSelectEvent(customer: Customer) {
    this.entity.customer = customer;
  }

  employeeSelectEvent(employee: Employee) {
    this.entity.salesman = employee;
  }

  productSelectEvent(product: Product) {
    this.productSelect = product;
    this.productPrice = product.salePrice.toFixed(2);
    this.focusById('qtsProduct');
    this.updateProductTotal();
  }

  private focusById(field: string) {
    setTimeout(()=>{
      document.getElementById(field).focus();
    },0);
  }

  updateProductTotal() {
    this.productPriceSubtotal = (Number(this.productPrice) * this.productQtd).toFixed(2);
  }

  serviceSelectEvent(serviceType: ServiceType) {
    this.serviceSelect = serviceType;
    this.servicePrice = serviceType.price.toFixed(2);
    this.focusById('qtsService');
    this.updateServiceTotal();
  }

  updateServiceTotal() {
    this.servicePriceSubtotal = (Number(this.servicePrice) * this.serviceQtd).toFixed(2);
  }

  async customerChangeEvent(name) {
    this.isLoadingCustomer = true;
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.customers = ((await this.cs.getAll(params).toPromise()) as Page<
      Customer
    >).content;
    this.isLoadingCustomer = false;
  }

  async employeeChangeEvent(name) {
    this.isLoadingEmployee = true;
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.employees = ((await this.es.getAll(params).toPromise()) as Page<
      Employee
    >).content;
    this.isLoadingEmployee = false;
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

  customerInputCleared() {
    this.customers = [];
    this.ngAutoCompleteCustomer.close();
  }

  employeeInputCleared() {
    this.employees = [];
    this.ngAutoCompleteEmployee.close();
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
      return `${status} - ${this.datePipe.transform(this.entity.saleDate, 'dd/MM/yyyy')}`.toUpperCase();
    }
    return '';
  }

  async reloadItems(page) {
    this.spinner.show();
    this.currentPage = page + 1;
    this.saleItems = (await this.ss.getSaleItens(this.entity.id, page).toPromise());
    this.spinner.hide();
  }

  async reloadIncomeItems(page) {
    this.spinner.show();
    this.incomeCurrentPage = page + 1;
    const params = new Map<string, string>();
    params.set('sale.id=', this.entity.id.toString());
    this.incomeItems = ((await this.is.getAll(params, page).toPromise()) as Page<
      Income
    >);
    this.spinner.hide();
  }

  onConfirmSave() {
    if (this.validEntity()) {
      const msg = `
        Confirma a inclusão da nova venda com os seguinte dados?<br>
        Cliente: ${this.entity.customer.name}<br>
        Vendedor: ${this.entity.salesman.name}<br><br>
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
    await this.ss.post(this.entity)
      .toPromise()
      .then(sale => {
        this.entity = sale;
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
            this.saleFinalize();
          }
        });
    }
  }

  private async saleFinalize() {
    await this.ss.saleFinalize(this.entity.id).toPromise()
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
    if (!this.saleItems || (this.saleItems && this.saleItems.content.length === 0)) {
      this.toastr.error(
        this.translate.instant('Não possuí itens para finalizar.'),
        this.translate.instant('Erro')
      );
      return false;
    }

    return true;
  }

  private validEntity() {
    if (!this.entity.customer) {
      this.toastr.error(
        this.translate.instant('Cliente não informado'),
        this.translate.instant('Erro')
      );
      return false;
    }

    if (!this.entity.salesman) {
      this.toastr.error(
        this.translate.instant('Vendedor não informado'),
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
      item.employeeId = this.entity.salesman.id;
      item.quantity = this.productQtd;
      await this.ss.addProductItem(this.entity.id, item).toPromise()
        .then(() => {
          this.reloadItems(0);
          this.productInputCleared();
          this.setSaleTotal();
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
      item.employeeId = this.entity.salesman.id;
      item.quantity = this.serviceQtd;
      await this.ss.addServiceItem(this.entity.id, item).toPromise()
        .then(() => {
          this.reloadItems(0);
          this.serviceInputCleared();
          this.setSaleTotal();
          this.toastr.success(
            this.translate.instant('Serviço adicionado com sucesso'),
            this.translate.instant('Sucesso')
          );
          this.spinner.hide();
        });
    }
  }

  confirmDeleteSaleItem(saleItem: SaleItem) {
    const value = getEnumTranslation(saleItem.itemType);
    openConfimDialog(new ConfirmDialogModel(`Confirma a remoção do ${value} ${saleItem.name}`)).content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogValues) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          if (saleItem.itemType === ItemType.PRODUCT) {
            this.deleteProductItem(saleItem);
          } else {
            this.deleteServiceItem(saleItem);
          }
        }
      });
  }

  confirmDeleteIncomeItem(income: Income) {
     openConfimDialog(new ConfirmDialogModel(`Confirma a remoção do pagamento`)).content.onClose
       .pipe(takeUntil(this.ngUnsubscribe))
       .subscribe((values: CloseDialogValues) => {
         if (values.action === ActionReturnDialog.CONFIRM) {
          this.deleteIncome(income);
         }
       });
  }

  private deleteIncome(income: Income) {
    this.spinner.show();
    this.is.delete(income).toPromise()
    .then(() => {
      this.reloadIncomeItems(0);
      this.ss.get(this.entity.id).toPromise()
      .then((sale: Sale) => {
        this.entity.status = sale.status;
        this.getTitle();
        this.setPanelHeaderStatus();
      });
      this.setSaleTotal();
      this.toastr.success(
        this.translate.instant('Pagamento removido com sucesso'),
        this.translate.instant('Sucesso')
      );
    });
    this.spinner.hide();
  }

  private deleteProductItem(saleItem: SaleItem) {
    this.spinner.show();
    this.ss.deleteProductItem(this.entity.id, saleItem.id).toPromise()
    .then(() => {
      this.reloadItems(0);
      this.setSaleTotal();
      this.toastr.success(
        this.translate.instant('Produto removido com sucesso'),
        this.translate.instant('Sucesso')
      );
    });
    this.spinner.hide();
  }

  private deleteServiceItem(saleItem: SaleItem) {
    this.spinner.show();
    this.ss.deleteServiceItem(this.entity.id, saleItem.id).toPromise()
    .then(() => {
      this.reloadItems(0);
      this.setSaleTotal();
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
          this.saleCancel();
        }
      });
  }

  private async saleCancel() {
    await this.ss.saleCancel(this.entity.id).toPromise()
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
    this.saleIncomeDto = new SaleIncomeDto();
    this.saleIncomeDto.paymentType = paymentTypeSelect;
  }

  private clearPaymentType() {
    this.removeParcels();
  }

  installment() {
    openSimpleDialog(new InstallmentDialog(this.saleIncomeDto.value), InstallmentDialogComponent)
      .content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogInstallmentValeus) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          this.hasParcels = true;
          this.salesIncomes = values.installmentDtos;
        }
      });
  }

  removeParcels() {
    this.salesIncomes = [];
    this.hasParcels = false;
  }

  launchPayment() {
    if (this.hasParcels) {
      this.ss.launchPaymentSalesIncomes(this.entity.id, this.salesIncomes)
        .toPromise()
        .then((response: SaleIncomeResponse) => {
          this.saleTotal.amountPaid = response.amountPaid;
          this.saleTotal.amountPayable = response.amountPayable;
          this.entity.status = response.sale.status;
          this.launchPaymentSucess();
        });
    } else {
      if (this.validLaunchPayment()) {
        this.ss.launchPaymentSaleIncome(this.entity.id, this.saleIncomeDto)
          .toPromise()
          .then((response: SaleIncomeResponse) => {
            this.saleTotal.amountPaid = response.amountPaid;
            this.saleTotal.amountPayable = response.amountPayable;
            this.entity.status = response.sale.status;
            this.launchPaymentSucess();
          });
      }
    }
  }

  private launchPaymentSucess() {
    this.reloadIncomeItems(0);
    this.setPanelHeaderStatus();
  }

  private validLaunchPayment() {
    if (!this.saleIncomeDto.value) {
      this.toastr.error(
        this.translate.instant('Nenhum valor informado'),
        this.translate.instant('Erro')
      );
      return false;
    }

    if (this.isCash || this.isBankCheck || this.isBankSlip) {
      if (!this.saleIncomeDto.duaDate) {
        this.toastr.error(
          this.translate.instant('Nenhuma data de vencimento informada'),
          this.translate.instant('Erro')
        );
        return false;
      }
    }

    if (this.isBankCheck()) {
      if (!this.saleIncomeDto.check.number) {
        this.toastr.error(
          this.translate.instant('Número do cheque não informado'),
          this.translate.instant('Erro')
        );
        return false;
      }

      if (!this.saleIncomeDto.check.emitter) {
        this.toastr.error(
          this.translate.instant('Emissor do cheque não informado'),
          this.translate.instant('Erro')
        );
        return false;
      }

      if (!this.saleIncomeDto.check.personDocument) {
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
      this.saleIncomeDto.duaDate = null;
    } else {
      const date = new Date();
      const separateDate = event.srcElement.value.split('-');
      date.setUTCDate(separateDate[2]);
      date.setUTCMonth(separateDate[1] - 1);
      date.setUTCFullYear(separateDate[0]);
      this.saleIncomeDto.duaDate = date;
    }
  }

  isCash() {
    return this.saleIncomeDto.paymentType === PaymentType.CASH;
  }

  isBankCheck() {
    return this.saleIncomeDto.paymentType === PaymentType.BANK_CHECK;
  }

  isCreditCard() {
    return this.saleIncomeDto.paymentType === PaymentType.CREDIT_CARD;
  }

  isBankSlip() {
    return this.saleIncomeDto.paymentType === PaymentType.BANK_SLIP;
  }

  isDebitCard() {
    return this.saleIncomeDto.paymentType === PaymentType.DEBIT_CARD;
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
