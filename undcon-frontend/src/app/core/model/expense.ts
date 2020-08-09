import { Entity } from '@model/entity';
import { Sale } from './sale';
import { PaymentStatus } from '../enum/payment-status';
import { PaymentType } from '../enum/payment-type';
import { Provider } from '@angular/core';

export class Expense extends Entity {
  description: string;
  duaDate: Date;
  paymentDate: Date;
  value: number;
  paymentStatus: PaymentStatus;
  sale: Sale;
  provider: Provider;
	paymentType: PaymentType;
}
