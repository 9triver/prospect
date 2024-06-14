import { type IRiskmanagement } from '@/shared/model/riskmanagement.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IRiskreport {
  id?: number;
  riskid?: number | null;
  type?: string | null;
  riskreportname?: string | null;
  releasetime?: Date | null;
  auditStatus?: keyof typeof AuditStatus | null;
  riskmanagement?: IRiskmanagement | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Riskreport implements IRiskreport {
  constructor(
    public id?: number,
    public riskid?: number | null,
    public type?: string | null,
    public riskreportname?: string | null,
    public releasetime?: Date | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public riskmanagement?: IRiskmanagement | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
