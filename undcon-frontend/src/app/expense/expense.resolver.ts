import { Injectable } from "@angular/core";

import { GetAllResolver } from "@shared/resolver/generic.resolver";
import { Expense } from '@app/core/model/expense';
import { ExpenseService } from '@app/core/service/expense/expense.service';

@Injectable()
export class ExpenseResolver extends GetAllResolver<Expense> {
  constructor(private service: ExpenseService) {
    super(service);
  }
}
