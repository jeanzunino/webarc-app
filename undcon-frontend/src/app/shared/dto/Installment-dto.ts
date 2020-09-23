import { PaymentType } from '@enum/payment-type';

export class InstallmentDto {
  duaDate: Date = new Date();
  paymentDate: Date;
  value: number;
  settled: boolean;
  paymentType: PaymentType;
}
