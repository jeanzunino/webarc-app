import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { ServiceTypeService } from '@service/service-type/service-type.service';
import { ServiceType } from '@model/service-type';
import { GenericListComponent } from '@app/core/component/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: './service-type.component.html',
  styleUrls: ['../core/component/generic-list.component.scss']
})
export class ServiceTypeComponent extends GenericListComponent<ServiceType> {

  constructor(private service: ServiceTypeService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }

}
