import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Provider } from '@model/provider';
import { ProviderService } from '@service/provider/provider.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-provider',
  templateUrl: './provider.component.html'
})
export class ProviderComponent extends GridViewComponent<Provider> {

  tableValues = new Table().set('id', 'provider.id').set('name', 'provider.name').set('phone', 'provider.phone').get();

  constructor(service: ProviderService,
              activatedRoute: ActivatedRoute) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    alert(item);
  }
}