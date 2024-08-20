import { type IOfficers } from '@/shared/model/officers.model';

export interface IDepartment {
  id?: string;
  name?: string | null;
  officersnum?: number | null;
  superior?: IDepartment | null;
  officers?: IOfficers[] | null;
}

export class Department implements IDepartment {
  constructor(
    public id?: string,
    public name?: string | null,
    public officersnum?: number | null,
    public superior?: IDepartment | null,
    public officers?: IOfficers[] | null,
  ) {}
}

export interface IDepartmentWithChildren extends IDepartment{
  children:IDepartmentWithChildren[]
} 