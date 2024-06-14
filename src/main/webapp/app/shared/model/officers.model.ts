import { type IDepartment } from '@/shared/model/department.model';
import { type IRole } from '@/shared/model/role.model';

export interface IOfficers {
  id?: number;
  officersid?: number | null;
  officersname?: string | null;
  password?: string | null;
  email?: string | null;
  phone?: number | null;
  department?: IDepartment | null;
  role?: IRole | null;
}

export class Officers implements IOfficers {
  constructor(
    public id?: number,
    public officersid?: number | null,
    public officersname?: string | null,
    public password?: string | null,
    public email?: string | null,
    public phone?: number | null,
    public department?: IDepartment | null,
    public role?: IRole | null,
  ) {}
}
