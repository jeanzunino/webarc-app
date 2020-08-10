import { Entity } from '@model/entity';
import { ProductCategory } from './product-category';

export class Product extends Entity {
  name: string;
  unit: string;
  purchasePrice: number;
  salePrice: number;
  stock: number;
  stockMin: number;
  productCategory: ProductCategory;
  gtin: number;
}
