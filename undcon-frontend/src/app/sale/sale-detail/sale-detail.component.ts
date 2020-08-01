import { NgxSpinnerService } from 'ngx-spinner';
import { Component, ViewEncapsulation, OnInit } from '@angular/core';
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

@Component({
  selector: 'app-sale-detail',
  templateUrl: './sale-detail.component.html',
  styleUrls: ['./sale-detail.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class SaleDetailComponent implements OnInit {

  spinner = SharedInjector.get(NgxSpinnerService);

  entity: Sale;
  colorPanelHeader = '';
  iconPanelHeader = '';

  customers: Customer[];
  customerKeyword = 'name';
  customerInitialValue;

  employees: Employee[];
  employeeKeyword = 'name';
  employeeInitialValue;

  constructor(private router: Router, private rt: ActivatedRoute, private cs: CustomerService) {
    this.loadPage();
  }

  private loadPage() {
    this.entity = this.rt.snapshot.data.entity as Sale;
    // this.customers = (this.rt.snapshot.data.customers as Page<Customer>).content;
    this.employees = (this.rt.snapshot.data.employees as Page<Employee>).content;
    if (!this.isNew()) {
      this.customerInitialValue = this.entity.customer;
      this.employeeInitialValue = this.entity.salesman;
      this.setPanelHeaderStatus();
    }
    this.spinner.hide();
  }

  private setPanelHeaderStatus() {
    debugger
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

  ngOnInit() {
    this.customerInitialValue = this.entity.customer;
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

  async teste(asd) {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), asd);
    this.customers = ((await this.cs.getAll(params).toPromise()) as Page<
      Customer
    >).content;
    console.log(this.customers)
  }

  public isNew() {
    return this.rt.snapshot.params.id === 'new';
  }
}
