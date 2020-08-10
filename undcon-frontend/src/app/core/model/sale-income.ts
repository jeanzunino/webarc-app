import { PaymentType } from '@enum/payment-type';

export class SaleIncome {
  duaDate: string;
  paymentDate: Date;
  value: number;
  settled: boolean;
  paymentType: PaymentType;
}
