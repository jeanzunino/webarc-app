import { Entity } from '@model/entity';

export class Product extends Entity {
    id: number;
    name: string;
    unit: string;
    purchasePrice: number;
    salePrice: number;
    stock: number;
    stockMin: number;
}
