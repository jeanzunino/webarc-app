import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Provider } from '@model/provider';
import { ProviderService } from '@service/provider/provider.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@model/page';

@Component({
  selector: 'app-provider',
  templateUrl: './provider.component.html'
})
export class ProviderComponent extends GridViewComponent <Provider> {

  tableValues = new Table().set('id', 'provider.id').set('name', 'provider.name').set('phone', 'provider.phone').get();

  constructor(spinner: NgxSpinnerService,
              service: ProviderService,
              activatedRoute: ActivatedRoute) {
              super(spinner, service, activatedRoute);
           }

  onClickItem(item) {
    alert(item);
  }

}
