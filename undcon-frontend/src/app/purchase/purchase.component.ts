import { Component, OnInit, OnDestroy } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { PurchaseService } from '@service/purchase/purchase.service';
import { Purchase } from '@model/purchase';
import { GenericListComponent } from '@component-generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../core/component/generic-list/generic-list.component.html',
  styleUrls: ['../core/component/generic-list/generic-list.component.scss']
})
export class PurchaseComponent extends GenericListComponent<Purchase> {

  constructor(private service: PurchaseService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
