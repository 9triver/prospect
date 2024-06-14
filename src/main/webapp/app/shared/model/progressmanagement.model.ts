import { type IDepartment } from '@/shared/model/department.model';
import { type IPlanreturns } from '@/shared/model/planreturns.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Progresstype } from '@/shared/model/enumerations/progresstype.model';
import { type Progressstatus } from '@/shared/model/enumerations/progressstatus.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProgressmanagement {
  id?: number;
  progressid?: number | null;
  progressname?: string | null;
  progresstype?: keyof typeof Progresstype | null;
  workfocus?: string | null;
  createtime?: Date | null;
  creatorname?: string | null;
  responsiblename?: string | null;
  status?: keyof typeof Progressstatus | null;
  baselineid?: number | null;
  auditStatus?: keyof typeof AuditStatus | null;
  department?: IDepartment | null;
  planreturns?: IPlanreturns | null;
  responsibleid?: IOfficers | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Progressmanagement implements IProgressmanagement {
  constructor(
    public id?: number,
    public progressid?: number | null,
    public progressname?: string | null,
    public progresstype?: keyof typeof Progresstype | null,
    public workfocus?: string | null,
    public createtime?: Date | null,
    public creatorname?: string | null,
    public responsiblename?: string | null,
    public status?: keyof typeof Progressstatus | null,
    public baselineid?: number | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public department?: IDepartment | null,
    public planreturns?: IPlanreturns | null,
    public responsibleid?: IOfficers | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
