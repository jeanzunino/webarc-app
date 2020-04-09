import { Component, OnInit, Input } from '@angular/core';
import { MDBModalRef } from 'angular-bootstrap-md';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit {

  @Input() modalTitle: string;
  @Input() modalRef: MDBModalRef;

  constructor(public translate: TranslateService) { }

  ngOnInit(): void {
    console.log(this.modalRef)
  }

}