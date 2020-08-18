import { Role } from "./role";
export class User {
    id: number;
    name: string;
    email: string;
    phone: string;

    createTime: string;
    updateTime: string;
    role: Role;
    activeFlag: number;
    token: string;
}
