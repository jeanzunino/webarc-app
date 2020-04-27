import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Sale } from '@model/sale';
import { SaleService } from '@service/sale/sale.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html'
})
export class SaleComponent extends GridViewComponent<Sale> {

  tableValues = new Table().set('id', 'sale.id').set('customer.name', 'sale.customer-name').set('saleDate', 'sale.saleDate').get();

  constructor(service: SaleService,
              activatedRoute: ActivatedRoute,
              modalService: MDBModalService) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    //this.openDialog(item, SaleEditComponent);
  }

  open() {
    this.onClickItem(null);
  }
}
