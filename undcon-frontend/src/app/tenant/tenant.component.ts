import { Component, OnInit, OnDestroy } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { TenantService } from '@service/tenant/tenant.service';
import { Tenant } from '@model/tenant';
import { GenericListComponent } from '@component-generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../core/component/generic-list/generic-list.component.html',
  styleUrls: ['../core/component/generic-list/generic-list.component.scss']
})
export class TenantComponent extends GenericListComponent<Tenant> {

  constructor(private service: TenantService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
