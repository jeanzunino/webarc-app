import { Entity } from '@model/entity';

export class ServiceType extends Entity {
    id: number;
    name: string;
    description: string;
    price: number;
}
