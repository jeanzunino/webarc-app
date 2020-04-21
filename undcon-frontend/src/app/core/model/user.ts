import { Employee } from '@model/employee';
import { Permission } from '@model/permission';

export class User {
    id: number;
    login: string;
    password: string;
    employee: Employee;
    permission: Permission;
    active: boolean;
}