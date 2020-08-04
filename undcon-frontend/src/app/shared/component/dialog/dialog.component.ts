import { Component, Input } from '@angular/core';
import { MDBModalRef } from 'angular-bootstrap-md';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html'
})
export class DialogComponent {
  @Input() modalTitle: string;
  @Input() modalRef: MDBModalRef;

  constructor(public translate: TranslateService) {}
}
