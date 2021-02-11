import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { getQueryFilter } from '@shared/utils/utils';
import { ServiceOrder } from '@app/core/model/service-order';
import { ServiceOrderService } from '@app/core/service/service-order/service-order.service';
import { Customer } from '@app/core/model/customer';
import { QueryFilterEnum } from '@app/core/enum/query-filter';
import { Page } from '@app/core/model/page';
import { CustomerService } from '@app/core/service/customer/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { SharedInjector } from '@app/shared/shared.module';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-service-order-edit',
  templateUrl: './service-order-edit.component.html'
})
export class ServiceOrderEditComponent implements OnInit, OnDestroy {

  private ngUnsubscribe = new Subject();
  
  spinner = SharedInjector.get(NgxSpinnerService);
  
  @ViewChild('ngAutoCompleteCustomer') ngAutoCompleteCustomer;
  customers: Customer[];
  customerKeyword = 'name';
  customerInitialValue;
  isLoadingCustomer = false;

  colorPanelHeader = '';
  iconPanelHeader = '';
  showPanelHeader = false;
  entity: ServiceOrder;
  formGroup: FormGroup;

  constructor(
    private router: Router,
    private rt: ActivatedRoute,
    private cs: CustomerService,
    private toastr: ToastrService,
    private translate: TranslateService,
    private so: ServiceOrderService
  ) {  }

  ngOnInit() {
    this.formGroup = this.createFormGroup();
    this.loadPage();
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      description: new FormControl('', Validators.required),
      defect: new FormControl('', Validators.required),
      guarantee: new FormControl('', Validators.required)
    });
  }

  private async loadPage() {
    this.entity = new ServiceOrder();
    if (!this.isNew()) {
      this.entity = this.rt.snapshot.data.entity as ServiceOrder;
      this.customerInitialValue = this.entity.customer;
      this.showPanelHeader = true;
    }
  }

  onConfirmSave() {
    if (!this.entity.customer) {
      this.toastr.error(
        this.translate.instant('Cliente não informado'),
        this.translate.instant('Erro')
      );
      return false;
    }
    this.onSave();
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

  customerSelectEvent(customer: Customer) {
    this.entity.customer = customer;
  }

  customerInputCleared() {
    this.customers = [];
    this.ngAutoCompleteCustomer.close();
  }

  public isNew() {
    return this.rt.snapshot.params.id === 'new';
  }

  public getTitle() {
    return '';
  }

  private async onSave() {
    this.spinner.show();
    await this.so.post(this.entity)
      .toPromise()
      .then(sale => {
        this.entity = sale;
        if (this.isNew()) {
          this.showPanelHeader = true;
          this.router.navigate(['../'], { relativeTo: this.rt.parent });
        }
        this.toastr.success(
          this.translate.instant('OS incluida com sucesso'),
          this.translate.instant('Sucesso')
        );
      });
    this.spinner.hide();
  }

  onDelete (){
      this.spinner.show();
      this.so.delete(this.entity).toPromise()
      .then(() => {
        this.toastr.success(
          this.translate.instant('Produto removido com sucesso'),
          this.translate.instant('Sucesso')
        );
        this.router.navigate(['../'], { relativeTo: this.rt.parent });
      });
      this.spinner.hide();
    }

}
