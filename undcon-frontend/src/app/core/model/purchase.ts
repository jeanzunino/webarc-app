import { User } from './user';
import { BillingStatus } from '@enum/billing-status';
import { Entity } from '@model/entity';
import { Provider } from '@model/provider';

export class Purchase extends Entity {
  provider: Provider;
  purchaseDate: Date;
  billed: boolean;
  status: BillingStatus;
  user: User;
}
