import { Entity } from '@model/entity';
import { Customer } from '@model/customer';
import { Employee } from '@model/employee';
import { BillingStatus } from '@app/core/enum/billing-status';

export class Sale extends Entity {
  customer: Customer;
  saleDate: Date;
  salesman: Employee;
  billed: boolean;
  status: BillingStatus;
}
