import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Page } from '@model/page';
import { Entity } from '@model/entity';
import { EntityService } from '@service/entity/entity.service';
import { SharedInjector } from '@shared/shared.module';
import { openDialog } from '@shared/utils/utils';


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

  openDialog(item: Entity, obj: Object) {
     this.modalRef = openDialog(item, obj);
  }

}
