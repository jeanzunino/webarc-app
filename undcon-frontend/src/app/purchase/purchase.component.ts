import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Purchase } from '@model/purchase';
import { PurchaseService } from '@service/purchase/purchase.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html'
})
export class PurchaseComponent extends GridViewComponent<Purchase> {

  tableValues = new Table().set('id', 'purchase.id').set('provider.name', 'purchase.name').set('purchaseDate', 'purchase.purchaseDate').get();

  constructor(service: PurchaseService,
              activatedRoute: ActivatedRoute) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    alert(item);
  }

}
