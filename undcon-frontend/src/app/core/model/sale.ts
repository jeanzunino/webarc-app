import { Customer } from '@model/customer';

export class Sale {
    id: number;
    customer: Customer;
    saleDate: Date;
}