export interface IPermission {
  id?: number;
  permissionid?: number | null;
  permissionname?: string | null;
  description?: string | null;
}

export class Permission implements IPermission {
  constructor(
    public id?: number,
    public permissionid?: number | null,
    public permissionname?: string | null,
    public description?: string | null,
  ) {}
}
