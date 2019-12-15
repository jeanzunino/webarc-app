import { Employee } from '@app/models/employee';

export class User {
    id: number;
    name: string;
    login: string;
    password: string;
    Employee: Employee
}