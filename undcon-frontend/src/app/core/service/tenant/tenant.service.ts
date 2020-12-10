import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Tenant } from "@model/tenant";
import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";

@Injectable({
  providedIn: "root",
})
export class TenantService extends EntityService<Tenant> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, "system/tenants");
  }

  public createDb(id: number){
    return this.http.post<any>(`${this.baseUrl}/${this.entityUrl}/${id}/createDb`, null);
  }
}
