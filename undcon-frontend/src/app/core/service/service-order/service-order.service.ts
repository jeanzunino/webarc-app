import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";
import { ServiceOrder } from "@app/core/model/service-order";

@Injectable({
  providedIn: "root",
})
export class ServiceOrderService extends EntityService<ServiceOrder> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, "servicesOrders");
  }
}
