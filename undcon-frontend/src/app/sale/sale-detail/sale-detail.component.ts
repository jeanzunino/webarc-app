import { InstallmentDialogComponent } from '@component/installment-dialog/installment-dialog.component';
import { openSimpleDialog } from '@shared/utils/utils';
import { SaleIncome } from './../../core/model/sale-income';
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
import { SaleStatus } from '@enum/sale-status';
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

@Component({
  selector: 'app-sale-detail',
  templateUrl: './sale-detail.component.html',
  styleUrls: ['./sale-detail.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class SaleDetailComponent implements OnDestroy {

  private ngUnsubscribe = new Subject();
  spinner = SharedInjector.get(NgxSpinnerService);
  showPanelHeader = false;

  saleItems: Page<SaleItem>;
  saleTotalValue = 0;
  currentPage = 0;
  tableValues = new Table()
    .set('name', 'sale-item.name')
    .set('price', 'sale-item.price', FormatEnum.MONEY)
    .set('quantity', 'sale-item.quantity')
    .set('itemType', 'sale-item.itemType', FormatEnum.ITEM_TYPE)
    .set('subTotalItem', 'sale-item.subTotalItem', FormatEnum.MONEY)
    .get();

  entity: Sale;
  colorPanelHeader = '';
  iconPanelHeader = '';
  bgPaymentTypeValues: ButtonGroupValues[];
  paymentTypeSelect = PaymentType.CASH;
  paymentIncome = new SaleIncome();
  reloadValuesPreviousScreen = false;
  hasParcels = false;
  paymentDuaDateLabel = 'Data de vencimeto';

  @ViewChild('ngAutoCompleteCustomer') ngAutoCompleteCustomer;
  customers: Customer[];
  customerKeyword = 'name';
  customerInitialValue;

  @ViewChild('ngAutoCompleteEmployee') ngAutoCompleteEmployee;
  employees: Employee[];
  employeeKeyword = 'name';
  employeeInitialValue;

  @ViewChild('ngAutoCompleteProduct') ngAutoCompleteProduct;
  private productSelect: Product;
  products: Product[];
  productKeyword = 'name';
  productPrice = '0';
  productQtd = 0;
  productPriceSubtotal = '0';

  @ViewChild('ngAutoCompleteService') ngAutoCompleteService;
  private serviceSelect: ServiceType;
  services: ServiceType[];
  serviceKeyword = 'name';
  servicePrice = '0';
  serviceQtd = 0;
  servicePriceSubtotal = '0';

  constructor(private router: Router,
              private rt: ActivatedRoute,
              private cs: CustomerService,
              private es: EmployeeService,
              private ps: ProductService,
              private sts: ServiceTypeService,
              private ss: SaleService,
              private datePipe: DatePipe,
              private toastr: ToastrService,
              private translate: TranslateService) {
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
      this.setSaleTotalValue();
      this.setBgPaymentType();
    }
  }

  setBgPaymentType() {
    const bgPaymentType = new ButtonGroup();
    Object.keys(PaymentType).filter((type) => isNaN(type as any) && type !== 'values')
      .forEach(paymentType => {
        bgPaymentType.set(paymentType, paymentType.toLowerCase());
      });
    this.bgPaymentTypeValues = bgPaymentType.get();
  }

  private async setSaleTotalValue() {
    this.saleTotalValue = (await this.ss.getSaleTotal(this.entity.id).toPromise()).totalValue;
  }

  private setPanelHeaderStatus() {
    switch (this.entity.status) {
      case SaleStatus.CREATED: {
        this.colorPanelHeader = 'background-color-deepskyblue';
        this.iconPanelHeader = 'fa fa-cart-plus';
        break;
      }
      case SaleStatus.TO_BILL: {
        this.colorPanelHeader = 'background-color-orange';
        this.iconPanelHeader = 'fa fa-exclamation-triangle';
        break;
      }
      case SaleStatus.BILLED: {
        this.colorPanelHeader = 'background-color-springgreen';
        this.iconPanelHeader = 'fa fa-check';
        break;
      }
      case SaleStatus.CANCELED: {
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

  async customerChangeEvent(name) {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.customers = ((await this.cs.getAll(params).toPromise()) as Page<
      Customer
    >).content;
  }

  async employeeChangeEvent(name) {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.employees = ((await this.es.getAll(params).toPromise()) as Page<
      Employee
    >).content;
  }

  async productChangeEvent(name) {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.products = ((await this.ps.getAll(params).toPromise()) as Page<
      Product
    >).content;
  }

  async serviceChangeEvent(name) {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), name);
    this.services = ((await this.sts.getAll(params).toPromise()) as Page<
      ServiceType
    >).content;
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
    return !this.isNew() ? `${this.entity.status.toUpperCase()} - ${this.datePipe.transform(this.entity.saleDate, 'dd/MM/yyyy hh:mm')}`
                         : '';
  }

  async reloadItems(page) {
    this.spinner.show();
    this.currentPage = page + 1;
    this.saleItems = (await this.ss.getSaleItens(this.entity.id, page).toPromise());
    this.spinner.hide();
  }

  isToBill() {
    return this.isNew() ? false : this.entity.status === SaleStatus.TO_BILL;
  }

  isBilled() {
    return this.isNew() ? false : this.entity.status === SaleStatus.BILLED;
  }

  isCanceled() {
    return this.isNew() ? false : this.entity.status === SaleStatus.CANCELED;
  }

  isCreated() {
    return this.isNew() ? false : this.entity.status === SaleStatus.CREATED;
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
            this.entity.status = SaleStatus.TO_BILL;
            this.onUpdateSale('Venda finalizada com sucesso');
          }
        });
    }
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
          this.setSaleTotalValue();
          this.toastr.success(
            this.translate.instant('Produto adicionado com sucesso'),
            this.translate.instant('Sucesso')
          );
        });
    }
    this.spinner.hide();
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
          this.setSaleTotalValue();
          this.toastr.success(
            this.translate.instant('Serviço adicionado com sucesso'),
            this.translate.instant('Sucesso')
          );
        });
    }
    this.spinner.hide();
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


  private deleteProductItem(saleItem: SaleItem) {
    this.spinner.show();
    this.ss.deleteProductItem(this.entity.id, saleItem.id).toPromise()
    .then(() => {
      this.reloadItems(0);
      this.setSaleTotalValue();
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
      this.setSaleTotalValue();
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
          this.entity.status = SaleStatus.CANCELED;
          this.onUpdateSale('Venda cancelada com sucesso');
        }
      });
  }

  onConfirmBill() {
    const msg = `Confirma o faturamento da venda?`;
    openConfimDialog(new ConfirmDialogModel(msg)).content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogValues) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          this.entity.status = SaleStatus.BILLED;
          this.onUpdateSale('Venda faturada com sucesso');
        }
      });
  }

  private async onUpdateSale(msgSucess: string) {
    this.spinner.show();
    await this.ss.put(this.entity)
      .toPromise()
      .then(sale => {
        this.entity = sale;
        this.setPanelHeaderStatus();
        this.reloadValuesPreviousScreen = true;
        this.toastr.success(
          this.translate.instant(msgSucess),
          this.translate.instant('Sucesso')
        );
      });
    this.spinner.hide();
  }

  bgPaymentTypeSelect(paymentTypeSelect: PaymentType) {
    debugger
    this.paymentTypeSelect = paymentTypeSelect;
    this.paymentIncome = new SaleIncome();
  }

  teste() {
    console.log(this.paymentIncome);
  }

  installment() {
    openSimpleDialog(new InstallmentDialog(this.paymentIncome.value), InstallmentDialogComponent)
      .content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogValues) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          this.hasParcels = true;
          this.paymentDuaDateLabel = '1ª Parcela';
          this.paymentIncome.duaDate = new Date();
        }
      });
  }

  isCash() {
    return this.paymentTypeSelect === PaymentType.CASH;
  }

  isBankCheck() {
    return this.paymentTypeSelect === PaymentType.BANK_CHECK;
  }

  isCreditCard() {
    return this.paymentTypeSelect === PaymentType.CREDIT_CARD;
  }

  isDebitCard() {
    return this.paymentTypeSelect === PaymentType.DEBIT_CARD;
  }
}
