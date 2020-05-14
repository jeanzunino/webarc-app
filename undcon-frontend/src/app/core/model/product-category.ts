import { Entity } from "@model/entity";

export class ProductCategory extends Entity {
  id: number;
  name: string;
  parent: ProductCategory;
}
