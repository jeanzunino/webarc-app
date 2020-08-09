import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";
import { Expense } from '@app/core/model/expense';

@Injectable({
  providedIn: "root",
})
export class ExpenseService extends EntityService<Expense> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, "expenses");
  }
}
