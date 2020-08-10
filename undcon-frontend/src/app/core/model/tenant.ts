import { Entity } from '@model/entity';
import { Salesman } from '@model/salesman';
import { ClientStatus } from '@enum/client-status';

export class Tenant extends Entity {
  name: string;
  email: string;
  phone: string;
  schemaName: string;
  salesman: Salesman;
  status: ClientStatus;
}
