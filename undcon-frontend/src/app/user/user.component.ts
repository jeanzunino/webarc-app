import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { User } from '@model/user';
import { UserService } from '@service/user/user.service';
import { UserEditComponent } from '@app/user/user-edit/user-edit.component';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@app/core/model/page';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html'
})
export class UserComponent extends GridViewComponent<User> {

  tableValues = new Table().set('id', 'user.id').set('login', 'user.login').set('permission.name', 'user.permission').get();
  modalRef: MDBModalRef;

  name: ''

  constructor(public userService: UserService,
              activatedRoute: ActivatedRoute,
              private modalService: MDBModalService) {
    super(userService, activatedRoute);
  }

  onClickItem(item) {
    this.spinner.show()
    this.modalRef = this.modalService.show(UserEditComponent, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered modal-lg',
      containerClass: '',
      animated: true,
      data: {
        content: item,
        isNew: false
      }
    });
  }

  open() {
    this.spinner.show()
    this.modalRef = this.modalService.show(UserEditComponent, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered modal-lg',
      containerClass: '',
      animated: true,
      data: {
        isNew: true
      }
    });
  }

  async onSearch() {
    this.spinner.show();
    this.items = await this.userService.getAll({
      login: this.name
    }).toPromise() as Page<User>;
    this.spinner.hide();
  }
}