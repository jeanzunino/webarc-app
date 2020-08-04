import { Entity } from '@model/entity';

export class SaleItem extends Entity {
  name: string;
  saleId: number;
  isProduct: boolean;
  userName: string;
  salesmanName: string;
  price: string;
  quantity: number;
}
