import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';
import { NgxSpinnerService } from 'ngx-spinner';

import { Sale } from '@model/sale';
import { PdvService } from '@service/pdv/pdv.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { OpenPdvComponent } from '@app/pdv/open-pdv/open-pdv.component';
import { Table } from '@shared/model/table';
import { SharedInjector } from '@shared/shared.module';
import { openDialog } from '@shared/utils/utils';
import { ClosePdvComponent } from './close-pdv/close-pdv.component';

@Component({
  selector: 'app-pdv',
  templateUrl: './pdv.component.html'
})
export class PdvComponent implements OnInit {

  spinner = SharedInjector.get(NgxSpinnerService);
  modalRef: MDBModalRef;

  constructor(service: PdvService,
              activatedRoute: ActivatedRoute,
              modalService: MDBModalService) {
  }

  ngOnInit() {
     this.spinner.hide();

     //TODO Verificar se o caixa está fechado
     let closed = false;
     if (closed) {
       this.modalRef = openDialog(null, OpenPdvComponent);
     }
  }

  public onClose() {
    this.modalRef = openDialog(null, ClosePdvComponent);
  }
}
