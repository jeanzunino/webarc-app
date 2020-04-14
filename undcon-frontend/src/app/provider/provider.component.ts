import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { ProviderService } from '@service/provider/provider.service';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-provider',
  templateUrl: './provider.component.html'
})
export class ProviderComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'provider.id').set('name', 'provider.name').set('phone', 'provider.phone').get();

  constructor(private spinner: NgxSpinnerService,
              public service: ProviderService,
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