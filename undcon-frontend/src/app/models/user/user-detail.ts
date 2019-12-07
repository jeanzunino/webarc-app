import { User } from '@models/user/user';

export class UserDetail {
    tenant: string;
    token: string;
    user: User;
}