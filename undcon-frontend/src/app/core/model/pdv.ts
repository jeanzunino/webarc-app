import { Entity } from '@model/entity';
import { User } from '@model/user';

export class Pdv extends Entity {
    id: number;
    openingDate: Date;
    responsibleUserInOpening: User;
    closingDate: Date;
    responsibleUserInClosing: User;
}
