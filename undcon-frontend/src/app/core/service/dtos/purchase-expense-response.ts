import { Expense } from '@app/core/model/expense';
import { Purchase } from '@model/purchase';

export interface PurchaseExpenseResponse {
  expensesCreated: Expense[];
  purchase: Purchase;
  amountPayable: number;
  amountPaid: number;
}
