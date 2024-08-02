import { type IRole } from '@/shared/model/role.model';

export interface IPermission {
  id?: string;
  permissionname?: string | null;
  description?: string | null;
  roles?: IRole[] | null;
}

export class Permission implements IPermission {
  constructor(
    public id?: string,
    public permissionname?: string | null,
    public description?: string | null,
    public roles?: IRole[] | null,
  ) {}
}
