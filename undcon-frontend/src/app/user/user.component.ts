import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { User } from '@model/user';
import { UserService } from '@service/user/user.service';
import { UserEditComponent } from '@app/user/user-edit/user-edit.component';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
})
export class UserComponent extends GridViewComponent<User> {
  tableValues = new Table()
    .set('login', 'user.login')
    .set('permission.name', 'user.permission')
    .get();
  modalRef: MDBModalRef;
  name = null;

  constructor(
    public userService: UserService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(userService, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, UserEditComponent);
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    this.onSearchParams({ page: 0, login: this.name });
  }

  onClear() {
    this.name = null;
    this.onClearParams();
  }
}
