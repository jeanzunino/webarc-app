import { Entity } from '@model/entity';
import { BillingStatus } from '../enum/billing-status';
import { PaymentStatus } from '../enum/payment-status';
import { ServiceOrderStatus } from '../enum/service-order-status-enum';
import { Customer } from './customer';
import { User } from './user';

export class ServiceOrder extends Entity {
  description: string;
  defect: string;
  guarantee: string;
  technicalReport: string;
  observations: string;
  customer: Customer;
  startDate: Date;
  endDate: Date;
  registerDate: Date;
  paymentStatus: PaymentStatus;
  status: ServiceOrderStatus;
  user: User;
}
