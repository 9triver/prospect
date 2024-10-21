import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IWorkbag } from '@/shared/model/workbag.model';

export interface IDepartment {
  id?: string;
  name?: string | null;
  description?: string | null;
  role?: string | null;
  officersnum?: number | null;
  superior?: IDepartment | null;
  officers?: IOfficers[] | null;
  pbs?: IProjectpbs[] | null;
  wbs?: IProjectwbs[] | null;
  workbags?: IWorkbag[] | null;
}

export class Department implements IDepartment {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public role?: string | null,
    public officersnum?: number | null,
    public superior?: IDepartment | null,
    public officers?: IOfficers[] | null,
    public pbs?: IProjectpbs[] | null,
    public wbs?: IProjectwbs[] | null,
    public workbags?: IWorkbag[] | null,
  ) {}
}
