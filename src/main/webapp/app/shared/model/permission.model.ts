export interface IPermission {
  id?: string;
  permissionname?: string | null;
  description?: string | null;
}

export class Permission implements IPermission {
  constructor(
    public id?: string,
    public permissionname?: string | null,
    public description?: string | null,
  ) {}
}
