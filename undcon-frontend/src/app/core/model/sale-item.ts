import { ItemType } from '@enum/item-type';
import { Entity } from '@model/entity';

export class SaleItem extends Entity {
  name: string;
  saleId: number;
  userName: string;
  salesmanName: string;
  price: string;
  quantity: number;
  subTotalItem: number;
  itemType: ItemType;
}
