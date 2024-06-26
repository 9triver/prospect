import { type IPermission } from '@/shared/model/permission.model';

export interface IRole {
  id?: string;
  rolename?: string | null;
  description?: string | null;
  permission?: IPermission | null;
}

export class Role implements IRole {
  constructor(
    public id?: string,
    public rolename?: string | null,
    public description?: string | null,
    public permission?: IPermission | null,
  ) {}
}
