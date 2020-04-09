import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalRef, MDBModalService } from 'angular-bootstrap-md';

import { UserService } from '@service/user/user.service';
import { User } from '@model/user';
import { GenericListComponent } from '@component/generic-list/generic-list.component';
import { UserEditComponent } from '@app/user/user-edit/user-edit.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class UserComponent extends GenericListComponent<User> {

  constructor(private service: UserService,
              activatedRoute: ActivatedRoute,
              private modalService: MDBModalService) {
      super(service, activatedRoute)
  }
  
  modalRef: MDBModalRef;

  openModal() {
    this.showDialog();
  }

  onClickItem(item) {
    this.showDialog(item)
  }

  getHeaderTitle() {
    return ['Id', 'Login'];
  }

  //Campos padrões caso a tela não implemente esse método
  getFieldsOfTable(item, header) {
    switch (header) {
      case 'Id': {
        return item.id;
      }
      case 'Login': {
        return item.login;
      }
      default: {
        return '';
      }
    }
  }

  private showDialog(user = null) {
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
        user: user
      }
    });
  }
}