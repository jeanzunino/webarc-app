import { Component, Input, OnDestroy } from '@angular/core';
import { MDBModalRef } from 'angular-bootstrap-md';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html'
})
export class DialogComponent implements OnDestroy {
  @Input() modalTitle: string;
  @Input() modalRef: MDBModalRef;

  constructor(public translate: TranslateService) {}

  ngOnDestroy() {
    document.body.style.overflow = 'auto';
  }
}
