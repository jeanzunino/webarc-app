import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ProviderService } from '@service/provider/provider.service';
import { Provider } from '@model/provider';
import { GenericListComponent } from '@component/generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class ProviderComponent extends GenericListComponent<Provider>  {

  constructor(service: ProviderService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}