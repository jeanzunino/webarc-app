import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { ServiceOrder } from '@app/core/model/service-order';
import { ServiceOrderService } from '@app/core/service/service-order/service-order.service';
import { ServiceOrderEditComponent } from './service-order-edit/service-order-edit.component';
import { ServiceOrderStatus } from '@app/core/enum/service-order-status-enum';
import { FormatEnum } from '@app/core/enum/format-enum';

@Component({
  selector: 'app-service-order',
  templateUrl: './service-order.component.html',
})
export class ServiceOrderComponent extends GridViewComponent<ServiceOrder> {
  tableValues = new Table()
    .set('id', 'service-order.id')
    .set('description', 'service-order.description')
    .set('customer.name', 'service-order.customer')
    .set('startDate', 'service-order.startDate')
    .set('status', 'service-order.status', FormatEnum.SERVICE_ORDER_STATUS)
    .get();
  id = null;  
  name = null;
  description = null;
  status: ServiceOrderStatus;

  statusList = Object.values(ServiceOrderStatus);

  constructor(
    service: ServiceOrderService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService,
    private router: Router,
    private rt: ActivatedRoute
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item: ServiceOrder) {
    this.router.navigate((item === null ? ['new'] : [item.id]), { relativeTo: this.rt });
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('id', QueryFilterEnum.EQUALS), this.id);
    params.set(getQueryFilter('customer.name', QueryFilterEnum.CONTAINS_IC), this.name);
    params.set(getQueryFilter('description', QueryFilterEnum.CONTAINS_IC), this.description);
    params.set(getQueryFilter('status', QueryFilterEnum.EQUALS), this.status);
    this.onSearchParams(params);
  }

  public onChangeStatus(status){
    this.status = status;
  }

  onClear() {
    this.description = null;
    this.onClearParams();
  }
}
