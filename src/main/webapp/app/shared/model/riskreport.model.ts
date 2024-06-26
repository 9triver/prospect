import { type IRiskidentification } from '@/shared/model/riskidentification.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IRiskreport {
  id?: string;
  type?: string | null;
  riskreportname?: string | null;
  releasetime?: Date | null;
  auditStatus?: keyof typeof AuditStatus | null;
  riskidentification?: IRiskidentification | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Riskreport implements IRiskreport {
  constructor(
    public id?: string,
    public type?: string | null,
    public riskreportname?: string | null,
    public releasetime?: Date | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public riskidentification?: IRiskidentification | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
