import { OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';
import { takeUntil } from 'rxjs/operators';
import { Subject, Subscription } from 'rxjs';


import { Page } from '@model/page';
import { Entity } from '@model/entity';
import { EntityService } from '@service/entity/entity.service';
import { SharedInjector } from '@shared/shared.module';
import { openDialog } from '@shared/utils/utils';

import { CloseDialogValues } from '@shared/model/close-dialog-values';

export abstract class GridViewComponent<T> implements OnInit, OnDestroy {
  spinner = SharedInjector.get(NgxSpinnerService);
  items: Page<T>;
  modalRef: MDBModalRef;
  currentPage = 1;
  private ngUnsubscribe = new Subject();
  private filterParams = new Map<string, string>();

  constructor(
    private service: EntityService<T>,
    private activatedRoute: ActivatedRoute,
    public modalService: MDBModalService
  ) {}

  sub: Subscription;

  ngOnInit() {
    this.sub = this.activatedRoute.params.subscribe(params => {
        if (params['reload']) {
          this.reloadItems(0);
        } else {
          this.loadItems();
        }
    });
    this.sub.unsubscribe();
  }

  private loadItems() {
    this.onSearch();
    //this.items = this.activatedRoute.snapshot.data.items as Page<T>;
    //this.spinner.hide();
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
    this.sub.unsubscribe();
  }

  async reloadItems(page) {
    this.spinner.show();
    this.currentPage = page + 1;

    this.onSearch();
  }

  openDialog(item: Entity, obj: Object) {
     this.modalRef = openDialog(item, obj);

     this.modalRef.content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogValues) => {
        if (values.hasChange) {
          this.reloadItems(0);
          this.currentPage = 1;
        }
      });
  }

  abstract onSearch();

  async onSearchParams(filters: Map<string, string>) {
    this.spinner.show();
    this.filterParams = filters;
    this.items = (await this.service.getAll(this.filterParams, this.currentPage - 1).toPromise()) as Page<T>;
    this.spinner.hide();
  }

  async onClearParams() {
    this.spinner.show();
    this.onSearch();
  }
}
