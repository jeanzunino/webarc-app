import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { User } from '@model/user';
import { UserService } from '@service/user/user.service';
import { UserEditComponent } from '@app/user/user-edit/user-edit.component';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@app/core/enum/format-enum';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
})
export class UserComponent extends GridViewComponent<User> {
  tableValues = new Table()
    .set('login', 'user.login')
    .set('employee.name', 'user.employee-name')
    .set('permission.name', 'user.permission')
    .set('active', 'user.active', FormatEnum.YES_NO)
    .set('tokenResetarSenha', 'user.tokenResetarSenha', FormatEnum.YES_NO)
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

  async onClickItem(item) {
    let user = null;
    if (item != null) {
      user = await this.userService.get(item.id).toPromise();
    }
    this.openDialog(user, UserEditComponent);
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), this.name);
    this.onSearchParams(params);
  }

  onClear() {
    this.name = null;
    this.onClearParams();
  }
}
