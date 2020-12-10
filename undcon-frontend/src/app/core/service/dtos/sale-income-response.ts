import { Sale } from '@model/sale';
import { Income } from '@model/income';

export interface SaleIncomeResponse {
  incomesCreated: Income[];
  sale: Sale;
  amountPayable: number;
  amountPaid: number;
}
