import { Entity } from '@model/entity';
import { Customer } from '@model/customer';

export class Sale extends Entity {
  customer: Customer;
  saleDate: Date;
}
