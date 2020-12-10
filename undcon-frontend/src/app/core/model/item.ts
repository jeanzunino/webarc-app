import { Entity } from '@model/entity';

export class Item extends Entity {
  itemId: number;
  quantity: number;
  employeeId: number;
}
