import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IQualitymanagement {
  id?: number;
  qualityid?: number | null;
  createtime?: Date | null;
  creatorname?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Qualitymanagement implements IQualitymanagement {
  constructor(
    public id?: number,
    public qualityid?: number | null,
    public createtime?: Date | null,
    public creatorname?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
