import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ServiceType } from '@model/service-type';
import { ServiceTypeService } from '@service/service-type/service-type.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-service-type',
  templateUrl: './service-type.component.html'
})
export class ServiceTypeComponent extends GridViewComponent<ServiceType>  {

  tableValues = new Table().set('id', 'service-type.id').set('name', 'service-type.name')
                           .set('description', 'service-type.description').set('price', 'service-type.price').get();

  constructor(service: ServiceTypeService,
              activatedRoute: ActivatedRoute) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    alert(item);
  }
}
