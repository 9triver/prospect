import { type IDepartment } from '@/shared/model/department.model';
import { type IFrontline } from '@/shared/model/frontline.model';
import { type IRole } from '@/shared/model/role.model';

import { type OfficersStatus } from '@/shared/model/enumerations/officers-status.model';
export interface IOfficers {
  id?: string;
  name?: string;
  password?: string | null;
  email?: string | null;
  phone?: number | null;
  hiredate?: Date | null;
  years?: number | null;
  status?: keyof typeof OfficersStatus | null;
  departments?: IDepartment[] | null;
  frontlines?: IFrontline[] | null;
  roles?: IRole[] | null;
}

export class Officers implements IOfficers {
  constructor(
    public id?: string,
    public name?: string,
    public password?: string | null,
    public email?: string | null,
    public phone?: number | null,
    public hiredate?: Date | null,
    public years?: number | null,
    public status?: keyof typeof OfficersStatus | null,
    public departments?: IDepartment[] | null,
    public frontlines?: IFrontline[] | null,
    public roles?: IRole[] | null,
  ) {}
}
