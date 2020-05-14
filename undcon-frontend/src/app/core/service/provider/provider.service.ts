import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Provider } from "@model/provider";
import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";

@Injectable({
  providedIn: "root",
})
export class ProviderService extends EntityService<Provider> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, "providers");
  }
}
