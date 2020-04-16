import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { ServiceType } from '@model/service-type';
import { ServiceTypeService } from '@service/service-type/service-type.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@model/page';

@Component({
  selector: 'app-service-type',
  templateUrl: './service-type.component.html'
})
export class ServiceTypeComponent extends GridViewComponent <ServiceType>  {

  tableValues = new Table().set('id', 'service-type.id').set('name', 'service-type.name')
                           .set('description', 'service-type.description').set('price', 'service-type.price').get();

  constructor(spinner: NgxSpinnerService,
              service: ServiceTypeService,
              activatedRoute: ActivatedRoute) {
                super(spinner, service, activatedRoute);
              }

  onClickItem(item) {
    alert(item);
  }
}
