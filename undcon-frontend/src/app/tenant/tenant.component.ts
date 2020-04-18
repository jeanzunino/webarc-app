import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { TenantService } from '@service/tenant/tenant.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Tenant } from '@model/tenant';

@Component({
  selector: 'app-tenant',
  templateUrl: './tenant.component.html'
})
export class TenantComponent extends GridViewComponent<Tenant> {

  tableValues = new Table().set('id', 'tenant.id').set('email', 'tenant.email').set('phone', 'tenant.phone').set('schemaName', 'tenant.schemaName').get();

  constructor(service: TenantService,
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
