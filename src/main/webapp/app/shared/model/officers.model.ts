import { type IDepartment } from '@/shared/model/department.model';

export interface IOfficers {
  id?: string;
  officersname?: string | null;
  password?: string | null;
  email?: string | null;
  phone?: number | null;
  departments?: IDepartment[] | null;
}

export class Officers implements IOfficers {
  constructor(
    public id?: string,
    public officersname?: string | null,
    public password?: string | null,
    public email?: string | null,
    public phone?: number | null,
    public departments?: IDepartment[] | null,
  ) {}
}
