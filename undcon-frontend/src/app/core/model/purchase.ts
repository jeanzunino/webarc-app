import { Entity } from "@model/entity";
import { Provider } from "@model/provider";

export class Purchase extends Entity {
  id: number;
  provider: Provider;
  purchaseDate: Date;
}
