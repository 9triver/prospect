import { type IPermission } from '@/shared/model/permission.model';

export interface IRole {
  id?: number;
  roleid?: number | null;
  rolename?: string | null;
  description?: string | null;
  permission?: IPermission | null;
}

export class Role implements IRole {
  constructor(
    public id?: number,
    public roleid?: number | null,
    public rolename?: string | null,
    public description?: string | null,
    public permission?: IPermission | null,
  ) {}
}
