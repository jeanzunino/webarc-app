import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Purchase } from '@model/purchase';
import { PurchaseService } from '@service/purchase/purchase.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@model/page';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html'
})
export class PurchaseComponent extends GridViewComponent <Purchase> {

  tableValues = new Table().set('id', 'purchase.id').set('provider.name', 'purchase.name').set('purchaseDate', 'purchase.purchaseDate').get();

  constructor(spinner: NgxSpinnerService,
              service: PurchaseService,
              activatedRoute: ActivatedRoute) {
              super(spinner, service, activatedRoute);
            }

  onClickItem(item) {
    alert(item);
  }

}
