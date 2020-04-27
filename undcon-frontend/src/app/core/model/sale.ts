import { Entity } from '@model/entity';
import { Customer } from '@model/customer';

export class Sale extends Entity {
    id: number;
    customer: Customer;
    saleDate: Date;
}
