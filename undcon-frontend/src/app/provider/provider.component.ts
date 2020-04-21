import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Provider } from '@model/provider';
import { ProviderService } from '@service/provider/provider.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { ProviderEditComponent } from '@app/provider/provider-edit/provider-edit.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-provider',
  templateUrl: './provider.component.html'
})
export class ProviderComponent extends GridViewComponent<Provider> {

  tableValues = new Table().set('id', 'provider.id').set('name', 'provider.name').set('phone', 'provider.phone').get();
  modalRef: MDBModalRef;

  constructor(service: ProviderService,
    activatedRoute: ActivatedRoute,
    private modalService: MDBModalService) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.modalRef = this.modalService.show(ProviderEditComponent, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered',
      containerClass: '',
      animated: true,
      data: {
        provider: item
      }
    });
  }
}
