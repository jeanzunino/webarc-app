import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Sale } from '@model/sale';
import { SaleService } from '@service/sale/sale.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@model/page';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html'
})
export class SaleComponent extends GridViewComponent <Sale> {

  tableValues = new Table().set('id', 'sale.id').set('customer.name', 'sale.customer-name').set('saleDate', 'sale.saleDate').get();

  constructor(spinner: NgxSpinnerService,
              service: SaleService,
              activatedRoute: ActivatedRoute) {
              super(spinner, service, activatedRoute);
             }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}
