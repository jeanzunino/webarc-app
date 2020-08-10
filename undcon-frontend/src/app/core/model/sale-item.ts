import { Entity } from '@model/entity';
import { ItemType } from '@enum/item-type';

export class SaleItem extends Entity {
  name: string;
  saleId: number;
  itemType: ItemType;
  userName: string;
  salesmanName: string;
  price: number;
  quantity: number;
  subTotalItem: number;
}
