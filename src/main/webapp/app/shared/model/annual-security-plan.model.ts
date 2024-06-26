import { type IProject } from '@/shared/model/project.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IAnnualSecurityPlan {
  id?: string;
  securityplanname?: string | null;
  releasetime?: Date | null;
  createtime?: Date | null;
  creatorname?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  version?: number | null;
  project?: IProject | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class AnnualSecurityPlan implements IAnnualSecurityPlan {
  constructor(
    public id?: string,
    public securityplanname?: string | null,
    public releasetime?: Date | null,
    public createtime?: Date | null,
    public creatorname?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public version?: number | null,
    public project?: IProject | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
