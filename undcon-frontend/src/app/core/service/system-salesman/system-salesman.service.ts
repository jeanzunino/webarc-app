import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Salesman } from "@model/salesman";
import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";

@Injectable({
  providedIn: "root",
})
export class SystemSalesmanService extends EntityService<Salesman> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, "system/salesmans");
  }
}
