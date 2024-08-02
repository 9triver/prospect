import { type IPermission } from '@/shared/model/permission.model';
import { type IOfficers } from '@/shared/model/officers.model';

export interface IRole {
  id?: string;
  rolename?: string | null;
  description?: string | null;
  permissions?: IPermission[] | null;
  officers?: IOfficers[] | null;
}

export class Role implements IRole {
  constructor(
    public id?: string,
    public rolename?: string | null,
    public description?: string | null,
    public permissions?: IPermission[] | null,
    public officers?: IOfficers[] | null,
  ) {}
}
