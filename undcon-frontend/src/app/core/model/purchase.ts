import { Provider } from '@model/provider';

export class Purchase {
    id: number;
    provider: Provider;
    purchaseDate: Date;
}