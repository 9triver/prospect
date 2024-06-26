import { type IOfficers } from '@/shared/model/officers.model';

export interface IDepartment {
  id?: string;
  departmentname?: string | null;
  officersnum?: number | null;
  officers?: IOfficers[] | null;
}

export class Department implements IDepartment {
  constructor(
    public id?: string,
    public departmentname?: string | null,
    public officersnum?: number | null,
    public officers?: IOfficers[] | null,
  ) {}
}
