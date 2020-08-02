import { NgxSpinnerService } from 'ngx-spinner';
import { Component, ViewEncapsulation, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Customer } from '@model/customer';
import { Sale } from '@model/sale';
import { Page } from '@model/page';
import { SharedInjector } from '@shared/shared.module';
import { Employee } from '@model/employee';
import { CustomerService } from '@app/core/service/customer/customer.service';
import { getQueryFilter } from '@shared/utils/utils';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { SaleStatus } from '@app/core/enum/sale-status';
import { FormGroup, FormControl } from '@angular/forms';
import { EmployeeService } from '@app/core/service/employee/employee.service';
import { Product } from '@app/core/model/product';
import { ProductService } from '@app/core/service/product/product.service';
import { ServiceType } from '@app/core/model/service-type';
import { ServiceTypeService } from '@app/core/service/service-type/service-type.service';

@Component({
  selector: 'app-sale-detail',
  templateUrl: './sale-detail.component.html',
  styleUrls: ['./sale-detail.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class SaleDetailComponent {

  spinner = SharedInjector.get(NgxSpinnerService);
  showPanelHeader = false;
  formGroup: FormGroup;

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
  products: Product[];
  productKeyword = 'name';
  productPrice = 0;
  productQtd = 0;
  productPriceTotal = 0;

  @ViewChild('ngAutoCompleteService') ngAutoCompleteService;
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
              private sts: ServiceTypeService) {
    this.loadPage();
  }

  private loadPage() {
    this.createFormGroup();
    this.entity = this.rt.snapshot.data.entity as Sale;
    if (!this.isNew()) {
      this.customerInitialValue = this.entity.customer;
      this.employeeInitialValue = this.entity.salesman;
      this.formGroup.patchValue({
        id: this.entity.id,
        saleDate: this.entity.saleDate
      });
      this.setPanelHeaderStatus();
      this.showPanelHeader = true;
    }
    this.spinner.hide();
  }

  createFormGroup() {
    this.formGroup = new FormGroup({
      id: new FormControl(null),
      saleDate: new FormControl('')
    });
  }

  private setPanelHeaderStatus() {
    switch (this.entity.status) {
      case SaleStatus.CREATED: {
        this.colorPanelHeader = 'background-color-deepskyblue';
        this.iconPanelHeader = 'fa fa-cart-plus';
        break;
      }
      case SaleStatus.TO_BILL: {
        this.colorPanelHeader = 'background-color-light-yellow';
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
    this.productPrice = product.salePrice;
    this.productPriceTotal = this.productPrice * this.productQtd;
  }

  updateProductTotal() {
    this.productPriceTotal = this.productPrice * this.productQtd;
  }

  serviceSelectEvent(serviceType: ServiceType) {
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
    this.ngAutoCompleteProduct.close();
  }

  serviceInputCleared() {
    this.services = [];
    this.ngAutoCompleteService.close();
  }

  get saleDateForm() {
    return this.formGroup.get('saleDate');
  }

  public isNew() {
    return this.rt.snapshot.params.id === 'new';
  }

  public getTitle() {
    return this.entity ? this.entity.status.toUpperCase() : '';
  }
}
