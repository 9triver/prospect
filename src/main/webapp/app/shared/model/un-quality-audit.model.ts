import { type IOfficers } from '@/shared/model/officers.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IUnQualityAudit {
  id?: string;
  unqualityname?: string | null;
  unqualitytype?: number | null;
  belongunitid?: string | null;
  belongunitname?: string | null;
  auditteam?: string | null;
  auditperson?: string | null;
  unqualitynum?: number | null;
  creatorname?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  inspector?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class UnQualityAudit implements IUnQualityAudit {
  constructor(
    public id?: string,
    public unqualityname?: string | null,
    public unqualitytype?: number | null,
    public belongunitid?: string | null,
    public belongunitname?: string | null,
    public auditteam?: string | null,
    public auditperson?: string | null,
    public unqualitynum?: number | null,
    public creatorname?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public inspector?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
