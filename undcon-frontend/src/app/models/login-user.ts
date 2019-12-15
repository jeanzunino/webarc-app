import { User } from '@app/models/user';

export class LoginUser {
    tenant: string;
    token: string;
    resetPassword: boolean;
    user: User;
}