import { Entity } from '@model/entity';

export class ProductCategory extends Entity {
  name: string;
  parent: ProductCategory;
}
