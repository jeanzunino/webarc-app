import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { TenantService } from '@service/tenant/tenant.service';
import { Tenant } from '@model/tenant';
import { GenericListComponent } from '@component/generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class TenantComponent extends GenericListComponent<Tenant> {

  constructor(service: TenantService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}