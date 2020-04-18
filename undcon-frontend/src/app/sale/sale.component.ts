import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

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
              activatedRoute: ActivatedRoute) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}