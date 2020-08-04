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
import { ConfirmDialogModel } from '@app/shared/model/confirm-dialog-model';
import { CloseDialogValues } from '@app/shared/model/close-dialog-values';
import { ActionReturnDialog } from '@enum/action-return-dialog';

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
  currentPage = 0;
  tableValues = new Table()
    .set('name', 'sale-item.name')
    .set('price', 'sale-item.price')
    .set('quantity', 'sale-item.quantity')
    .set('isProduct', 'sale-item.type', FormatEnum.IS_PRODUCT)
    .set('subTotalItem', 'sale-item.subTotalItem')
    .get();

  entity: Sale;
  colorPanelHeader = '';
  iconPanelHeader = '';

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
  productPrice = 0;
  productQtd = 0;
  productPriceTotal = 0;

  @ViewChild('ngAutoCompleteService') ngAutoCompleteService;
  private serviceSelect: ServiceType;
  services: ServiceType[];
  serviceKeyword = 'name';
  servicePrice = 0;
  serviceQtd = 0;
  servicePriceTotal = 0;

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

  private loadPage() {
    this.entity = new Sale();
    if (!this.isNew()) {
      this.entity = this.rt.snapshot.data.entity as Sale;
      this.customerInitialValue = this.entity.customer;
      this.employeeInitialValue = this.entity.salesman;
      this.setPanelHeaderStatus();
      this.showPanelHeader = true;
      this.saleItems = this.rt.snapshot.data.saleItens;
    }
    this.spinner.hide();
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
    const previousRoute = '../';
    this.router.navigate([previousRoute], { relativeTo: this.rt.parent });
  }

  customerSelectEvent(customer: Customer) {
    this.entity.customer = customer;
  }

  employeeSelectEvent(employee: Employee) {
    this.entity.salesman = employee;
  }

  productSelectEvent(product: Product) {
    this.productSelect = product;
    this.productPrice = product.salePrice;
    this.productPriceTotal = this.productPrice * this.productQtd;
  }

  updateProductTotal() {
    this.productPriceTotal = this.productPrice * this.productQtd;
  }

  serviceSelectEvent(serviceType: ServiceType) {
    this.serviceSelect = serviceType;
    this.servicePrice = serviceType.price;
    this.servicePriceTotal = this.servicePrice * this.serviceQtd;
  }

  serviceProductTotal() {
    this.servicePriceTotal = this.servicePrice * this.serviceQtd;
  }

  updateServiceTotal() {
    this.servicePriceTotal = this.servicePrice * this.serviceQtd;
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
    this.productQtd = 0;
    this.productPriceTotal = 0;
    this.ngAutoCompleteProduct.close();
  }

  serviceInputCleared() {
    this.services = [];
    this.serviceSelect = null;
    this.serviceQtd = 0;
    this.servicePriceTotal = 0;
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

  async onSave() {
    if (this.validEntity()) {
      await this.ss.post(this.entity)
      .toPromise()
      .then(sale => {
        this.entity = sale;
        this.setPanelHeaderStatus();
        if (this.isNew()) {
          this.showPanelHeader = true;
          this.router.navigate(['../', this.entity.id], { relativeTo: this.rt.parent });
        }
        this.toastr.success(
          this.translate.instant('Venda incluida com sucesso'),
          this.translate.instant('Sucesso')
        );
      });
    }
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
    if (this.validProduct()) {
      const item = new Item();
      item.itemId = this.productSelect.id;
      item.employeeId = this.entity.salesman.id;
      item.quantity = this.productQtd;
      await this.ss.addProductItem(this.entity.id, item).toPromise()
        .then(() => {
          this.reloadItems(0);
          this.productInputCleared();
          this.toastr.success(
            this.translate.instant('Produto adicionado com sucesso'),
            this.translate.instant('Sucesso')
          );
        });
    }
  }

  async addService() {
    if (this.validService()) {
      const item = new Item();
      item.itemId = this.serviceSelect.id;
      item.employeeId = this.entity.salesman.id;
      item.quantity = this.serviceQtd;
      await this.ss.addServiceItem(this.entity.id, item).toPromise()
        .then(() => {
          this.reloadItems(0);
          this.serviceInputCleared();
          this.toastr.success(
            this.translate.instant('Serviço adicionado com sucesso'),
            this.translate.instant('Sucesso')
          );
        });
    }
  }

  confirmDeleteSaleItem(saleItem: SaleItem) {
    const value = saleItem.isProduct ? 'produto' : 'serviço';
    openConfimDialog(new ConfirmDialogModel(`Confirma a remoção do ${value} ${saleItem.name}`)).content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogValues) => {
        if (values.action === ActionReturnDialog.CONFIRM) {
          if (saleItem.isProduct) {
            this.deleteProductItem(saleItem);
          } else {
            this.deleteServiceItem(saleItem);
          }
        }
      });
  }

  deleteProductItem(saleItem: SaleItem) {
    this.ss.deleteProductItem(this.entity.id, saleItem.id).toPromise()
    .then(() => {
      this.reloadItems(0);
      this.toastr.success(
        this.translate.instant('Produto removido com sucesso'),
        this.translate.instant('Sucesso')
      );
    });
  }

  deleteServiceItem(saleItem: SaleItem) {
    this.ss.deleteServiceItem(this.entity.id, saleItem.id).toPromise()
    .then(() => {
      this.reloadItems(0);
      this.toastr.success(
        this.translate.instant('Serviço removido com sucesso'),
        this.translate.instant('Sucesso')
      );
    });
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
}
