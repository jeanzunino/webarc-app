import { Entity } from '@model/entity';
import { Provider } from '@model/provider';

export class Purchase extends Entity {
  provider: Provider;
  purchaseDate: Date;
}
