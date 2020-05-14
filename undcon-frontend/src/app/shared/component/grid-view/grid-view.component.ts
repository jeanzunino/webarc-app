import { OnInit, OnDestroy } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { NgxSpinnerService } from "ngx-spinner";
import { MDBModalService, MDBModalRef } from "angular-bootstrap-md";
import { takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";

import { EntityService } from "@service/entity/entity.service";
import { Page } from "@model/page";
import { SharedInjector } from "@shared/shared.module";
import { CloseDialogValues } from "@shared/model/close-dialog-values";

export abstract class GridViewComponent<T> implements OnInit, OnDestroy {
  spinner = SharedInjector.get(NgxSpinnerService);
  items: Page<T>;
  modalRef: MDBModalRef;
  currentPage = 0;
  private ngUnsubscribe = new Subject();
  private filterParams = null;

  constructor(
    private service: EntityService<T>,
    private activatedRoute: ActivatedRoute,
    public modalService: MDBModalService
  ) {}

  ngOnInit() {
    this.items = this.activatedRoute.snapshot.data.items as Page<T>;
    this.spinner.hide();
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  async reloadItems(page) {
    this.spinner.show();
    this.currentPage = page + 1;

    let params = { page };
    if (this.filterParams) {
      this.filterParams.page = page;
      params = this.filterParams;
    }
    this.items = (await this.service.getAll(params).toPromise()) as Page<T>;
    this.spinner.hide();
  }

  openDialog(item: T, obj: Object) {
    this.modalRef = this.modalService.show(obj, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: "modal-dialog-centered",
      containerClass: "",
      animated: true,
      data: {
        content: item,
      },
    });

    this.modalRef.content.onClose
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((values: CloseDialogValues) => {
        if (values.hasChange) {
          this.reloadItems(0);
          this.currentPage = 1;
        }
      });
  }

  async onSearchParams(params: {}) {
    this.spinner.show();
    this.filterParams = params;
    this.items = (await this.service.getAll(params).toPromise()) as Page<T>;
    this.currentPage = 1;
    this.spinner.hide();
  }

  async onClearParams() {
    this.spinner.show();
    this.filterParams = null;
    this.items = (await this.service.getAll({ page: 0 }).toPromise()) as Page<
      T
    >;
    this.currentPage = 1;
    this.spinner.hide();
  }
}
