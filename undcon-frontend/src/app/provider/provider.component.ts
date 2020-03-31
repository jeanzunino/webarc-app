import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { ProviderService } from '@service/provider/provider.service';
import { Provider } from '@model/provider';
import { GenericListComponent } from '@component-generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../core/component/generic-list/generic-list.component.html',
  styleUrls: ['../core/component/generic-list/generic-list.component.scss']
})
export class ProviderComponent extends GenericListComponent<Provider>  {

  constructor(private service: ProviderService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
