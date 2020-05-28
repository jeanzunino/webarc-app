import { Entity } from '@model/entity';

export class Tenant extends Entity {
  name: string;
  email: string;
  phone: string;
  schemaName: string;
}
