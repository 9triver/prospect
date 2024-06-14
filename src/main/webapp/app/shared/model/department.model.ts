export interface IDepartment {
  id?: number;
  departmentid?: number | null;
  departmentname?: string | null;
  officersnum?: number | null;
  officersid?: string | null;
}

export class Department implements IDepartment {
  constructor(
    public id?: number,
    public departmentid?: number | null,
    public departmentname?: string | null,
    public officersnum?: number | null,
    public officersid?: string | null,
  ) {}
}
