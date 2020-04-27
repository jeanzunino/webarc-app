import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { EntityService } from '@service/entity/entity.service';
import { Page } from '@model/page';
import { SharedInjector } from '@shared/shared.module';

export abstract class GridViewComponent<T> implements OnInit {

  spinner = SharedInjector.get(NgxSpinnerService);
  items: Page<T>;
  modalRef: MDBModalRef;

  constructor(private service: EntityService<T>,
              private activatedRoute: ActivatedRoute,
              public modalService: MDBModalService) { }

  ngOnInit() {
    this.items = this.activatedRoute.snapshot.data.items as Page<T>;
    this.spinner.hide();
  }

  async reloadItems(page) {
    this.spinner.show()
    this.items = await this.service.getAll({
      page: page
    }).toPromise() as Page<T>;
    this.spinner.hide()
  }

  openDialog(item: T, obj: Object) {
    this.modalRef = this.modalService.show(obj, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered',
      containerClass: '',
      animated: true,
      data: {
        content: item
      }
    });
  }

}
