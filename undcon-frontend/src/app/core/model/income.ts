import { Entity } from '@model/entity';
import { Sale } from './sale';
import { Customer } from './customer';
import { PaymentStatus } from '../enum/payment-status';
import { PaymentType } from '../enum/payment-type';

export class Income extends Entity {
  description: string;
  duaDate: Date;
  paymentDate: Date;
  value: number;
  paymentStatus: PaymentStatus;
  sale: Sale;
  customer: Customer;
  paymentType: PaymentType;
}
