import { User } from "@model/user";

export class LoginUser {
  tenant: string;
  token: string;
  resetPassword: boolean;
  user: User;
}
