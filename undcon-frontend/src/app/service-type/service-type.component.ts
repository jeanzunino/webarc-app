import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ServiceTypeService } from '@service/service-type/service-type.service';
import { ServiceType } from '@model/service-type';
import { GenericListComponent } from '@component/generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class ServiceTypeComponent extends GenericListComponent<ServiceType> {

  constructor(service: ServiceTypeService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}