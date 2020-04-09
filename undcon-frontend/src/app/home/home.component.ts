import { Component, OnInit } from '@angular/core';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';
import { UserEditComponent } from '@app/user/user-edit/user-edit.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private modalService: MDBModalService) { }

  ngOnInit() {
  }
  modalRef: MDBModalRef;
  showDialog() {
    alert('FOI')
    this.modalRef = this.modalService.show(UserEditComponent, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered',
      containerClass: '',
      animated: true,
    });
  }

}