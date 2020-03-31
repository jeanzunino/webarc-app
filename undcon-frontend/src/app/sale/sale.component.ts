import { Component, OnInit, OnDestroy } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { SaleService } from '@service/sale/sale.service';
import { Sale } from '@model/sale';
import { GenericListComponent } from '@component-generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../core/component/generic-list/generic-list.component.html',
  styleUrls: ['../core/component/generic-list/generic-list.component.scss']
})
export class SaleComponent extends GenericListComponent<Sale> {

  constructor(private service: SaleService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
