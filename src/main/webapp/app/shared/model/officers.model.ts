import { type IDepartment } from '@/shared/model/department.model';
import { type IRole } from '@/shared/model/role.model';

import { type OfficersStatus } from '@/shared/model/enumerations/officers-status.model';
export interface IOfficers {
  id?: number;
  hiredate?: Date | null;
  years?: number | null;
  status?: keyof typeof OfficersStatus | null;
  departments?: IDepartment[] | null;
  roles?: IRole[] | null;
}

export class Officers implements IOfficers {
  constructor(
    public id?: number,
    public hiredate?: Date | null,
    public years?: number | null,
    public status?: keyof typeof OfficersStatus | null,
    public departments?: IDepartment[] | null,
    public roles?: IRole[] | null,
  ) {}
}
