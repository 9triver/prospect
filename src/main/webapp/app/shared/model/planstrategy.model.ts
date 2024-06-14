import { type IDepartment } from '@/shared/model/department.model';
import { type IOfficers } from '@/shared/model/officers.model';

export interface IPlanstrategy {
  id?: number;
  strategyid?: string | null;
  strategyname?: string | null;
  number?: number | null;
  type?: string | null;
  decument?: IDepartment | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Planstrategy implements IPlanstrategy {
  constructor(
    public id?: number,
    public strategyid?: string | null,
    public strategyname?: string | null,
    public number?: number | null,
    public type?: string | null,
    public decument?: IDepartment | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
