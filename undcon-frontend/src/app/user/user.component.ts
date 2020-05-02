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

  tableValues = new Table().set('login', 'user.login').set('permission.name', 'user.permission').get();
  modalRef: MDBModalRef;

  name: ''

  constructor(public userService: UserService,
              activatedRoute: ActivatedRoute,
              modalService: MDBModalService) {
    super(userService, activatedRoute, modalService);
    this.modalService.closed.subscribe(async () => {
      this.spinner.show()
      this.items = await this.userService.getAll({
        page: 0
      }).toPromise() as Page<any>;
      this.spinner.hide();
    })
  }

  onClickItem(item) {
    this.openDialog(item, UserEditComponent);
  }

  open() {
    this.onClickItem(null);
  }

  async onSearch() {
    this.spinner.show();
    this.items = await this.userService.getAll({
      login: this.name
    }).toPromise() as Page<User>;
    this.spinner.hide();
  }
}
