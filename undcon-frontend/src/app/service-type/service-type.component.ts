import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { ServiceTypeService } from '@service/service-type/service-type.service';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-service-type',
  templateUrl: './service-type.component.html'
})
export class ServiceTypeComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'service-type.id').set('name', 'service-type.name')
                           .set('description', 'service-type.description').set('price', 'service-type.price').get();

  constructor(private spinner: NgxSpinnerService,
              public service: ServiceTypeService,
              private modalService: MDBModalService) { }

  modalRef: MDBModalRef;            

  async ngOnInit() {
    this.items = await this.service.getAll().toPromise();
    this.spinner.hide();
  }

  async reloadItems(page) {
    this.spinner.show()
    this.items = await this.service.getAll(page).toPromise();
    this.spinner.hide()
  }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}