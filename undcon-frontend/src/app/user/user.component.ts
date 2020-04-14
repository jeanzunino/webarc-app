import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { UserService } from '@service/user/user.service';
import { UserEditComponent } from '@app/user/user-edit/user-edit.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html'
})
export class UserComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'user.id').set('login', 'user.login').set('permission.name', 'user.permission').get();

  constructor(private spinner: NgxSpinnerService,
              public service: UserService,
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
    this.modalRef = this.modalService.show(UserEditComponent, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered',
      containerClass: '',
      animated: true,
      data: {
        user: item
      }
    });
  }
}