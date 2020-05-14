import { Component } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { MDBModalService } from "angular-bootstrap-md";

import { ServiceType } from "@model/service-type";
import { ServiceTypeService } from "@service/service-type/service-type.service";
import { GridViewComponent } from "@component/grid-view/grid-view.component";
import { ServiceTypeEditComponent } from "@app/service-type/service-type-edit/service-type-edit.component";
import { Table } from "@shared/model/table";

@Component({
  selector: "app-service-type",
  templateUrl: "./service-type.component.html",
})
export class ServiceTypeComponent extends GridViewComponent<ServiceType> {
  tableValues = new Table()
    .set("name", "service-type.name")
    .set("description", "service-type.description")
    .set("price", "service-type.price")
    .get();

  constructor(
    service: ServiceTypeService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, ServiceTypeEditComponent);
  }

  open() {
    this.onClickItem(null);
  }
}
