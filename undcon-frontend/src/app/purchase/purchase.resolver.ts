import { ExpenseService } from './../core/service/expense/expense.service';
import { IncomeService } from '@service/income/income.service';
import { ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Purchase } from '@model/purchase';
import { PurchaseService } from '@service/purchase/purchase.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';

@Injectable()
export class PurchaseResolver extends GetAllResolver<Purchase> {
  constructor(service: PurchaseService) {
    super(service);
  }
}

@Injectable()
export class PurchaseDetailResolver {
  constructor(private purchaseService: PurchaseService) {}

    public resolve(route: ActivatedRouteSnapshot) {
      if (route.params.id === 'new') {
        return;
      }
      return this.purchaseService.get(route.params.id);
    }
}

@Injectable()
export class PurchaseItensResolver {
  constructor(private purchaseService: PurchaseService) {}

    public resolve(route: ActivatedRouteSnapshot) {
      if (route.params.id === 'new') {
        return;
      }
      return this.purchaseService.getPurchaseItens(route.params.id, 0);
    }
}

@Injectable()
export class PurchaseExpenseResolver {
  constructor(private expenseService: ExpenseService) {}

    public resolve(route: ActivatedRouteSnapshot) {
      if (route.params.id === 'new') {
        return;
      }
      const params = new Map<string, string>();
      params.set('purchase.id=', route.params.id);
      return this.expenseService.getAll(params, 0).toPromise();
    }
}
