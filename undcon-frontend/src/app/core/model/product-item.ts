import { Entity } from '@model/entity';

export class ProductItem extends Entity {
  productId: number;
  quantity: number;
  employeeId: number;
}
