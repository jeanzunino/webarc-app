import { Entity } from '@model/entity';

export class BankCheck extends Entity {
  number: string;
  emitter: string;
  personDocument: string;
}
