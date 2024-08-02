import { type IOfficers } from '@/shared/model/officers.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IRiskReport {
  id?: string;
  type?: string | null;
  riskreportname?: string | null;
  releasetime?: Date | null;
  auditStatus?: keyof typeof AuditStatus | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class RiskReport implements IRiskReport {
  constructor(
    public id?: string,
    public type?: string | null,
    public riskreportname?: string | null,
    public releasetime?: Date | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
