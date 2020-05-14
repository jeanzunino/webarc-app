import { Entity } from "@model/entity";

export class Tenant extends Entity {
  id: number;
  name: string;
  email: string;
  phone: string;
  schemaName: string;
}
