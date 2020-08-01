import { Entity } from '@model/entity';
import { Customer } from '@model/customer';
import { Employee } from '@model/employee';
import { SaleStatus } from '@enum/sale-status';

export class Sale extends Entity {
  customer: Customer;
  saleDate: Date;
  salesman: Employee;
  billed: boolean;
  status: SaleStatus;
}
