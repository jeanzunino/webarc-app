import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Employee } from "@model/employee";
import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";

@Injectable({
  providedIn: "root",
})
export class EmployeeService extends EntityService<Employee> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, "employees");
  }
}
