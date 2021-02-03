import { Entity } from '@model/entity';
import { Employee } from '@model/employee';
import { Permission } from '@model/permission';

export class User extends Entity {
  login: string;
  password: string;
  employee: Employee;
  permission: Permission;
  active: boolean;
  tokenResetarSenha: string;
}
