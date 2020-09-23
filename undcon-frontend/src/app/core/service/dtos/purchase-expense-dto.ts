import { BankCheckDto } from './bank-check-dto';
import { PaymentType } from '@enum/payment-type';

export class PurchaseExpenseDto {
  duaDate: Date = new Date();
  paymentDate: Date;
  value = 0;
  settled = false;
  paymentType = PaymentType.CASH;
  check = new BankCheckDto();
}
